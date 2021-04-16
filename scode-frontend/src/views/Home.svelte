<script>
import { onMount } from 'svelte';
import { articleApi } from '../api';
import { formatDate } from '../utils';
import notifier from '../utils/notifier';

let articles = [];

onMount(async () => {
    const response = await articleApi.list();
    const body     = response.data;

    if (!body.ok) {
        notifier.error('Cannot fetch article list');
    }else{
        articles = body.data;
    }
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
</div>
<div class="pagination py-1 mb-2">
    <div class="container-fluid g-0">
        <div class="row">
            <div class="col-6">
                <button class="btn btn-primary">Prev</button>
            </div>
            <div class="col-6 text-end">
                <button class="btn btn-primary">Next</button>
            </div>
        </div>
    </div>
</div>
