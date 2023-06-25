import { defineConfig } from 'astro/config';
import cloudflare from "@astrojs/cloudflare";
import Icons from 'unplugin-icons/vite';
import unocss from "@unocss/astro";
import presetUno from '@unocss/preset-uno'

// https://astro.build/config
export default defineConfig({
  output: "server",
  adapter: cloudflare(),
  integrations: [
    unocss({
      injectReset: true,
      presets: [
        presetUno(),
      ]
    })
  ],
  vite: {
    plugins: [
      Icons({
        compiler: 'astro',
        autoInstall: true
      })
    ]
  }
});