<script>
import { push } from 'svelte-spa-router';
import { trimData } from '../../utils';
import validator from '../../utils/validator';
import notifier from '../../utils/notifier';
import { articleApi } from '../../api';

let data = {
    title  : { value: "", ref: null },
    content: { value: "", ref: null },
}

const handlePublish = async () => {
    data = trimData(data);

    const rules = {
        'title'  : 'isNotEmpty',
        'content': 'isNotEmpty',
    }

    if (validator.passes(data, rules)) {
        try {
            const response = await articleApi.create({
                title  : data.title.value,
                content: data.content.value,
            });

            const body = response.data;

            if (!body.ok) {
                notifier.error(body.message);
                return;
            }else{
                notifier.ok("Create success, Your article published");

                push("/");
            }
        }catch(e) {
            notifier.warn('Unknown Error when create article');

            console.log(e);
        }
    }
}
</script>

<div id="article article-create">
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
                    <button type="submit" class="btn btn-primary" on:click={handlePublish}>Publish</button>
                </div>
            </div>
        </div>
    </div>
</div>
