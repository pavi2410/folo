import { eq, lte } from "drizzle-orm";
import { DbType } from "../db";
import { profileHistory, profiles } from "../db/schema";
import { fetchProfile } from "./platforms/index";

export async function updateStaleProfiles(db: DbType) {
    const currentTimestamp = Date.now();
    const twentyFourHoursAgo = new Date(currentTimestamp - 24 * 60 * 60 * 1000);

    const staleProfiles = await db.select().from(profiles).where(
        lte(profiles.lastFetchedAt, twentyFourHoursAgo)
    );

    for (const profile of staleProfiles) {
        const metrics = await fetchProfile(profile.platform as any, profile.username)

        if (!metrics) {
            console.error("Failed to fetch profile for", profile.platform, profile.username);
            continue;
        }

        const historyData = Object.entries(metrics).map(([metric, value]) => ({
            profileId: profile.id,
            metric,
            value: typeof value === 'number' ? value : 0,
        }));

        await db.insert(profileHistory).values(historyData);

        await db.update(profiles).set({
            lastFetchedAt: new Date(),
        }).where(
            eq(profiles.id, profile.id)
        );
    }
}

export async function updateProfile(profileId: number, db: DbType) {
    const profile = await db.select().from(profiles).where(eq(profiles.id, profileId)).limit(1);

    if (!profile.length) {
        console.error("profile doesnt exist for", profileId)
        return
    }

    const metrics = await fetchProfile(profile[0].platform as any, profile[0].username)

    if (!metrics) {
        console.error("Failed to fetch profile for", profile[0].platform, profile[0].username);
        return;
    }

    const historyData = Object.entries(metrics).map(([metric, value]) => ({
        profileId: profile[0].id,
        metric,
        value: typeof value === 'number' ? value : 0,
    }));

    await db.insert(profileHistory).values(historyData);

    await db.update(profiles).set({
        lastFetchedAt: new Date(),
    }).where(
        eq(profiles.id, profile[0].id)
    );
}
