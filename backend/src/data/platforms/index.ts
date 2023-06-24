import { fetchGithubProfile } from "./github";
import { fetchTwitterProfile } from "./twitter";
import { fetchYoutubeProfile } from "./youtube";

export type ProfileResult = {
  platform: string;
  username: string;
  followers: number;
  updatedAt: number;
};

type ProfileFetcher = (username: string) => Promise<ProfileResult | null>;

export const platforms = new Map<string, ProfileFetcher>();

platforms.set("github", fetchGithubProfile);
platforms.set("twitter", fetchTwitterProfile);
platforms.set("youtube", fetchYoutubeProfile);
