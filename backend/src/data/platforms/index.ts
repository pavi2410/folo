import { fetchGithubProfile } from './github';
import { fetchThreadsProfile } from './threads';
import { fetchTwitterProfile } from './twitter';
import { fetchYoutubeProfile } from './youtube';

export type ProfileMetric = {
	metric: string;
	value: number;
};

export type ProfileResult = {
	platform: string;
	username: string;
	metrics: ProfileMetric[];
	updatedAt: number;
};

export const PLATFORMS = {
	github: fetchGithubProfile,
	threads: fetchThreadsProfile,
	twitter: fetchTwitterProfile,
	youtube: fetchYoutubeProfile,
} as const;

export type PlatformNames = keyof typeof PLATFORMS;

export const platformNames = Object.keys(PLATFORMS) as ['github', 'threads', 'twitter', 'youtube'];

export async function fetchProfile(platform: PlatformNames, username: string): Promise<ProfileResult | null> {
	const platformFetcher = PLATFORMS[platform];

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
