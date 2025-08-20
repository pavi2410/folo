import app from './server';
import { Bindings } from './bindings';
import { runCronJob } from './cronjob';

export default {
  fetch: app.fetch,
  async scheduled(event: ScheduledEvent, env: Bindings, ctx: ExecutionContext) {
    ctx.waitUntil(runCronJob(env));
  }
};
