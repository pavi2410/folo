import type { ProfileResult } from ".";

export async function fetchGithubProfile(
  username: string
): Promise<ProfileResult | null> {
  const url = new URL("https://github.com/" + username);

  const res = await fetch(url);
  const html = await res.text();

  const matches = html.match(/>(\d+)<\/span>\s+followers/);
  if (!matches) {
    return null;
  }
  const followers = parseInt(matches[1]) ?? -1;

  return {
    platform: "github",
    username,
    followers,
    updatedAt: Date.now(),
  };
}
