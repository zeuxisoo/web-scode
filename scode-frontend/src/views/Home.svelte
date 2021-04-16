<script>
import { onMount } from 'svelte';
import { articleApi } from '../api';
import { formatDate } from '../utils';
import notifier from '../utils/notifier';
import Pagination from '../components/Pagination.svelte';

let articles = [];
let meta     = {};

const fetchArticles = async page => {
    const response = await articleApi.list({ page });
    const body     = response.data;

    if (!body.ok) {
        notifier.error('Cannot fetch article list');
    }else{
        articles = body.data;
        meta     = body.meta;
    }
}

const handleFetchArticles = e => {
    fetchArticles(e.detail.page);
}

onMount(async () => {
    fetchArticles(0);
});
</script>


<style lang="postcss">
.title {
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 2px;
}

.datetime {
    color: #B1B1B1;
    font-size: 14px;
    margin-bottom: 2px;
}

.content {
    margin-top: 10px;
}
</style>

<div class="articles py-1">
    {#each articles as article}
        <div class="card mb-2">
            <div class="card-body">
                <div class="title">{article.title}</div>
                <div class="datetime">{formatDate(article.created_at)}</div>
                <div class="content">
                    {article.content}
                </div>
            </div>
        </div>
    {:else}
        <div class="card mb-2">
            <div class="card-body">
                Loading ...
            </div>
        </div>
    {/each}

    {#if articles.length > 0}
        <Pagination data={meta.pagination} on:fetchArticles={handleFetchArticles} />
    {/if}
</div>
