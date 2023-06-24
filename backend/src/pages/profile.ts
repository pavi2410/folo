import type { APIContext } from "astro";

import { fetchTwitterProfile } from "../data/platforms/twitter.js";
import { fetchYoutubeProfile } from "../data/platforms/youtube.js";
import { platforms } from "../data/platforms/index.js";

export async function get({ url }: APIContext) {
  const platform = url.searchParams.get("platform");
  const username = url.searchParams.get("username");

  if (platform === null || username === null) {
    return new Response("Missing platform or username", {
      status: 400,
    });
  }

  const platformFetcher = platforms.get(platform);
  if (!platformFetcher) {
    return new Response("Invalid platform", {
      status: 400,
    });
  }
  const profile = await platformFetcher(username);

  return new Response(JSON.stringify(profile), {
    status: 200,
    headers: {
      "Content-Type": "application/json",
    },
  });
}
