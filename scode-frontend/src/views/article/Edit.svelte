<script>
import { onMount } from 'svelte';
import { push } from 'svelte-spa-router';
import { articleApi } from '../../api';
import notifier from '../../utils/notifier';

export let params = {};

const articleId = params?.id;

let data = {
    title  : { value: "", ref: null },
    content: { value: "", ref: null },
}

onMount(async () => {
    try {
        const response = await articleApi.show(articleId);
        const body     = response.data;

        if (!body.ok) {
            notifier.error(body.message);

            push('/');
        }else{
            const article = body.data;

            data.title.value   = article.title;
            data.content.value = article.content;
        }
    }catch(e) {
        notifier.warn('Unknown Error when fetch article by id');

        push('/');

        console.log(e);
    }
})
</script>

<div id="article article-edit">
    <div class="card">
        <div class="card-body">
            <div class="row g-3">
                <div class="col-12">
                    <label for="title" class="form-label">Title</label>
                    <!-- svelte-ignore a11y-autofocus -->
                    <input type="text" class="form-control" id="title" placeholder="your title" autofocus bind:value={data.title.value} bind:this={data.title.ref}>
                </div>
                <div class="col-12">
                    <label for="content" class="form-label">Content</label>
                    <textarea class="form-control" id="content" placeholder="your content" rows="10" bind:value={data.content.value} bind:this={data.content.ref}></textarea>
                </div>
                <div class="col-12">
                    <button type="submit" class="btn btn-warning">Update</button>
                </div>
            </div>
        </div>
    </div>
</div>
