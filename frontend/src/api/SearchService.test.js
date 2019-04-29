import SearchService from "./SearchService";

it("handles non-connection errors correctly", async () => {
    await expect(fetch("http://httpstat.us/500")
                .then(res => SearchService.handleErrors(res)))
                    .rejects.toThrow("500");
}, 10000);

it("gets posts without error", async () => {
    const results = await SearchService.getPosts("java");
    expect(results.rating).toBeDefined();
    expect(results.posts).toBeDefined();
}, 10000);

it("gets valid rating value", async () => {
    const results = await SearchService.getPosts("python");
    expect(results.rating).toBeLessThanOrEqual(1);
    expect(results.rating).toBeGreaterThanOrEqual(-1);
}, 10000);

it("gets valid posts", async () => {
    const results = await SearchService.getPosts("scala");
    const firstPost = results.posts[0];
    expect(firstPost).toBeDefined();
    expect(firstPost.score).toBeDefined();
    expect(firstPost.timestamp).toBeDefined();
    expect(firstPost.content).toBeDefined();
    expect(firstPost.url).toBeDefined();
}, 10000);

it("gets posts that contain valid values", async () => {
    const results = await SearchService.getPosts("ruby");
    const firstPost = results.posts[0];
    expect(firstPost.score).toBeLessThanOrEqual(1);
    expect(firstPost.score).toBeGreaterThanOrEqual(-1);
    expect(firstPost.timestamp).toMatch(/^\d{4}[/-](0?[1-9]|1[012])[/-](0?[1-9]|[12][0-9]|3[01])$/);
    expect(typeof firstPost.content).toEqual('string');
    expect(firstPost.url).toMatch(/^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)?[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/);
}, 10000);

it("gets time series without error", async () => {
    const results = await SearchService.getTimeSeries("php");
    expect(results.timestamps).toBeDefined();
    expect(results.medians).toBeDefined();
}, 10000);

it("gets time series with valid values", async () => {
    const results = await SearchService.getTimeSeries("haskell");
    expect(results.timestamps[0]).toMatch(/^\d{4}[/-](0?[1-9]|1[012])[/-](0?[1-9]|[12][0-9]|3[01])$/);
    expect(results.medians[0]).toBeLessThanOrEqual(1);
    expect(results.medians[0]).toBeGreaterThanOrEqual(-1);
}, 10000);