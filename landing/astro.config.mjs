import { defineConfig } from 'astro/config';
import cloudflare from "@astrojs/cloudflare";
import Icons from 'unplugin-icons/vite';
import unocss from "@unocss/astro";
import presetUno from '@unocss/preset-uno'

import tailwindcss from '@tailwindcss/vite';

import react from '@astrojs/react';

// https://astro.build/config
export default defineConfig({
  integrations: [
    react()
  ],
  vite: {
    plugins: [
      Icons({
        compiler: 'astro',
        autoInstall: true
      }),
      tailwindcss()
    ]
  }
});