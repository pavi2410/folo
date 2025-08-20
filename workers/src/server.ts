import { Hono } from 'hono';
import { logger } from 'hono/logger';
import { zValidator } from '@hono/zod-validator';
import { z } from 'zod';
import { fetchProfile, platformNames } from './data/platforms/index.js';
import { createDb } from './db';
import { profiles, profileHistory } from './db/schema';
import { Bindings } from './bindings';
import { updateProfile } from './data/profile';

const app = new Hono<{ Bindings: Bindings }>();
app.use('*', logger());

app.get('/', (c) => c.text('Hello Cloudflare Workers!'));

app.get('/profiles', async (c) => {
	const db = createDb(c.env.DB);
	const allProfiles = await db.select().from(profiles);
	return c.json(allProfiles);
});

app.post('/profiles',
	zValidator('json', z.object({
		platform: z.string(),
		username: z.string(),
	})),
	async (c) => {
		const { platform, username } = c.req.valid('json');
		const db = createDb(c.env.DB);

		const newProfile = await db.insert(profiles).values({
			platform,
			username,
			lastFetchedAt: new Date(),
		}).returning();

		return c.json(newProfile[0]);
	}
);

app.get('/notify', async (c) => {
	const { profile_id } = c.req.query()
	console.log('Notified about', profile_id)
	const db = createDb(c.env.DB);
	await updateProfile(Number(profile_id), db)
	return c.body(null)
});

app.get(
	'/profile',
	zValidator(
		'query',
		z.object({
			platform: z.literal(platformNames),
			username: z.string(),
		})
	),
	async (c) => {
		const { platform, username } = c.req.valid('query');

		const profile = await fetchProfile(platform, username);

		return c.json(profile);
	}
);

export default app;
