import {db} from './db.js';
import {getProfile} from "./worker.js";

// Get current timestamp in milliseconds
const currentTimestamp = Date.now();

// Calculate timestamp for 24 hours ago
const twentyFourHoursAgo = new Date(currentTimestamp - 24 * 60 * 60 * 1000);

let profilesCollection = db.collection("profiles");
const querySnapshot = await profilesCollection
    .where("last_fetched", "<", twentyFourHoursAgo)
    .get();

for (const doc of querySnapshot.docs) {
    // doc.data() is never undefined for query doc snapshots
    let profile = doc.data()

    console.log(profile);

    // Get a reference to the "history" subcollection of the current profile document
    const historyCollection = doc.ref.collection('history');

    console.time('processing time')

    let metrics = await getProfile(profile)
    
    for (const [metric, value] of Object.entries(metrics)) {
        // Data to be added to the "history" subcollection
        const historyData = {
            timestamp: new Date(),
            value,
            metric
        };

        // Add a new document to the "history" subcollection
        historyCollection.add(historyData);
    }

    await doc.ref.update({
        last_fetched: new Date(),
    });

    console.timeEnd('processing time')
}
