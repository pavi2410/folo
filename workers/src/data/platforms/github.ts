import type { ProfileResult } from '.';

const REGEX = {
	followers: />(\d+)<\/span>\s+followers/,
};

export async function fetchGithubProfile(username: string): Promise<ProfileResult['metrics'] | null> {
	const url = new URL('https://github.com/' + username);

	const res = await fetch(url);
	const html = await res.text();

	const matches = html.match(REGEX.followers);
	if (!matches) {
		return null;
	}
	const followers = parseInt(matches[1]) ?? -1;

	return {
		followers,
	};
}
