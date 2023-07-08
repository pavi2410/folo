import { Hono } from 'hono';
import { logger } from 'hono/logger';
import { zValidator } from '@hono/zod-validator';
import { z } from 'zod';
import { fetchProfile, platformNames } from './data/platforms/index.js';

const app = new Hono();
app.use('*', logger());

app.get('/', (c) => c.text('Hello Cloudflare Workers!'));

app.get(
	'/profile',
	zValidator(
		'query',
		z.object({
			platform: z.enum(platformNames),
			username: z.string(),
			metric: z.string(),
		})
	),
	async (c) => {
		const { platform, username, metric } = c.req.valid('query');

		const profile = await fetchProfile(platform, username);

		return c.json(profile);
	}
);

export default app;
