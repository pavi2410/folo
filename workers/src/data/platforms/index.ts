import * as githubModule from './github';
import * as redditModule from './reddit';
import * as threadsModule from './threads';
import * as twitterModule from './twitter';
import * as youtubeModule from './youtube';

export type ProfileResult = {
	platform: string;
	username: string;
	metrics: Record<string, number>;
	updatedAt: number;
};

export const PLATFORMS = {
	github: githubModule,
	reddit: redditModule,
	threads: threadsModule,
	twitter: twitterModule,
	youtube: youtubeModule,
} as const;

export type PlatformNames = keyof typeof PLATFORMS;

export const platformNames = Object.keys(PLATFORMS) as PlatformNames[];

export async function fetchProfile(platform: PlatformNames, username: string): Promise<ProfileResult | null> {
	const platformFetcher = PLATFORMS[platform].fetchProfile;

	const metrics = await platformFetcher(username);

	if (!metrics) {
		return null;
	}

	return {
		platform,
		username,
		metrics,
		updatedAt: Date.now(),
	};
}
