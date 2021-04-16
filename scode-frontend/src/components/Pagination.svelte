<script>
import { createEventDispatcher } from 'svelte';

export let data;

const dispatch = createEventDispatcher();

const handleChangePage = page => event => {
    dispatch('fetchArticles', { page });
}
</script>

<div class="pagination py-1 mb-2">
    <div class="container-fluid g-0">
        <div class="row">
            <div class="col-12">
                {#if data?.links?.prev}
                    <div class="float-start">
                        <button class="btn btn-primary" on:click={handleChangePage(data?.current_page - 1)}>Prev</button>
                    </div>
                {/if}
                {#if data?.links?.next}
                    <div class="float-end">
                        <button class="btn btn-primary" on:click={handleChangePage(data?.current_page + 1)}>Next</button>
                    </div>
                {/if}
            </div>
        </div>
    </div>
</div>
