// Import the functions you need from the SDKs you need
import {applicationDefault, cert, initializeApp} from "firebase-admin/app";
import {getFirestore} from "firebase-admin/firestore";

const FIREBASE_ADMIN_KEY = JSON.parse(process.env.FIREBASE_ADMIN_KEY);

// Initialize Firebase
const app = initializeApp({
    credential: cert(FIREBASE_ADMIN_KEY),
});
export const db = getFirestore(app);
