<script>
export let data;

$: prevLink = `#/?page=${data?.current_page - 1}`;
$: nextLink = `#/?page=${data?.current_page + 1}`;
</script>

<div class="pagination py-1 mb-2">
    <div class="container-fluid g-0">
        <div class="row">
            <div class="col-12">
                {#if data?.links?.prev}
                    <div class="float-start">
                        <a class="btn btn-primary" href={prevLink}>Prev</a>
                    </div>
                {/if}
                {#if data?.links?.next}
                    <div class="float-end">
                        <a class="btn btn-primary" href={nextLink}>Next</a>
                    </div>
                {/if}
            </div>
        </div>
    </div>
</div>
