<script>
export let params = {};

const articleId = params?.id;

// TODO: fetch artile by article id and save the update data
console.log(articleId);
</script>

<div id="article article-edit">
    <div class="card">
        <div class="card-body">
            <div class="row g-3">
                <div class="col-12">
                    <label for="title" class="form-label">Title</label>
                    <!-- svelte-ignore a11y-autofocus -->
                    <input type="text" class="form-control" id="title" placeholder="your title" autofocus>
                </div>
                <div class="col-12">
                    <label for="content" class="form-label">Content</label>
                    <textarea class="form-control" id="content" placeholder="your content" rows="10"></textarea>
                </div>
                <div class="col-12">
                    <button type="submit" class="btn btn-warning">Update</button>
                </div>
            </div>
        </div>
    </div>
</div>
