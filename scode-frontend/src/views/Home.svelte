<script>
import { querystring } from 'svelte-spa-router';
import qs from 'qs';
import { useDefaultContext } from '../context/default';
import { articleApi } from '../api';
import { formatDate } from '../utils';
import notifier from '../utils/notifier';
import Pagination from '../components/Pagination.svelte';

const defaultContext = useDefaultContext();

let isLoading = false;
let articles  = [];
let meta      = {};

const fetchArticles = async page => {
    isLoading = true;

    const response = await articleApi.list({ page });
    const body     = response.data;

    if (!body.ok) {
        notifier.error('Cannot fetch article list');
    }else{
        articles = body.data;
        meta     = body.meta;
    }

    isLoading = false;
}

// When $querystring changed then fetch articles again by page
$: fetchArticles(qs.parse($querystring)?.page ?? 0);
</script>

<style lang="postcss">
.title {
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 2px;
}

.detail {
    color: #B1B1B1;
    font-size: 14px;
    margin-bottom: 2px;
}

.content {
    margin-top: 10px;
}

.text-edit {
    color: #C599F5;
}
</style>

<div class="articles py-1">
    {#if isLoading}
        <div class="card mb-2">
            <div class="card-body">
                Loading ...
            </div>
        </div>
    {:else}
        {#each articles as article}
            <div class="card mb-2">
                <div class="card-body">
                    <div class="title">{article.title}</div>
                    <div class="detail">
                        <div class="row">
                            <div class="col-8">
                                &#10561; {formatDate(article.created_at)}
                                ▪
                                &#64; {article.username}
                            </div>
                            <div class="col-4 text-end">
                                {#if $defaultContext.isAuthenticated}
                                    <a class="edit text-edit" href="#/article/edit/{article.id}">[Edit]</a>
                                {/if}
                            </div>
                        </div>
                    </div>
                    <div class="content">
                        {article.content}
                    </div>
                </div>
            </div>
        {:else}
            <div class="card mb-2">
                <div class="card-body">
                    No article :(
                </div>
            </div>
        {/each}

        {#if articles.length > 0}
            <Pagination data={meta.pagination} />
        {/if}
    {/if}
</div>
