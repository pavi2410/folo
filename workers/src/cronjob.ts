import { createDb } from "./db";
import { updateStaleProfiles } from "./data/profile";
import { Bindings } from "./bindings";

export async function runCronJob(env: Bindings) {
    const db = createDb(env.DB);
    await updateStaleProfiles(db);
}
