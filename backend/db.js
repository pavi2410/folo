// Import the functions you need from the SDKs you need
import {applicationDefault, initializeApp} from "firebase-admin/app";
import {getFirestore} from "firebase-admin/firestore";

// Initialize Firebase
const app = initializeApp({
    credential: applicationDefault(),
});
export const db = getFirestore(app);
