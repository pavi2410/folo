// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyAezckH5pwTBiAb3f-lJ2Mu-AOJ9i_qWYg",
  authDomain: "folo-acc8b.firebaseapp.com",
  projectId: "folo-acc8b",
  storageBucket: "folo-acc8b.appspot.com",
  messagingSenderId: "995306907734",
  appId: "1:995306907734:web:5601379eafffe4270db3f7",
  measurementId: "G-CNYWPMR75T"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
