<style lang="postcss" global>
@import "bootstrap";

body {
    padding-top: 15px;
}

.header, .menu {
    border-bottom: 1px solid #e5e5e5;
}

.nav-scroller {
    position: relative;
    z-index: 2;
    height: 44px;
    overflow-y: hidden;
}

.articles {
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
}
</style>

<div id="app">
    <div class="container">
        <div class="header d-flex justify-content-center">
            <h1>SCode</h1>
        </div>
        <div class="menu py-1 mb-2">
            <div class="nav-scroller">
                <div class="nav d-flex justify-content-between">
                    <a class="p-2 link-secondary" href="/">Home</a>
                    <a class="p-2 link-secondary" href="/">Register</a>
                    <a class="p-2 link-secondary" href="/">Sign in</a>
                </div>
            </div>
        </div>
        <div class="articles py-1 mb-2">
            <div class="card">
                <div class="card-body">
                    <div class="title">Title</div>
                    <div class="datetime">2021-04-09 16:24 @ Author</div>
                    <div class="content">
                        Content
                    </div>
                </div>
            </div>
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
    </div>
</div>
