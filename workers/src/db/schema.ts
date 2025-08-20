import { sql } from 'drizzle-orm';
import { sqliteTable, text, int, unique } from 'drizzle-orm/sqlite-core';

export const profiles = sqliteTable('profiles', {
	id: int('id').primaryKey({ autoIncrement: true }),
	platform: text('platform').notNull(),
	username: text('username').notNull(),
	lastFetchedAt: int('last_fetched_at', { mode: 'timestamp' }).notNull().default(sql`(unixepoch())`).$onUpdate(() => sql`(unixepoch())`),
}, (t) => [
	unique().on(t.platform, t.username),
]);

export const profileHistory = sqliteTable('profile_history', {
	id: int('id').primaryKey({ autoIncrement: true }),
	profileId: int('profile_id').references(() => profiles.id),
	metric: text('metric').notNull(),
	value: int('value').notNull(),
	timestamp: int('timestamp', { mode: 'timestamp' }).notNull().default(sql`(unixepoch())`).$onUpdate(() => sql`(unixepoch())`),
}, (t) => [
	unique().on(t.profileId, t.metric, t.timestamp),
]);
