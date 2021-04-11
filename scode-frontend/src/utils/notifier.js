import toastr from 'toastr';

const cheer = (() => {
    toastr.options = {
       ...toastr.options,

       closeButton: true,
       preventDuplicates: true,
       progressBar: true,
       positionClass: "toast-top-right",
    };

    return toastr;
})();

const notifier = {
    ok: (message, title) => cheer.success(message, title),
    error: (message, title) => cheer.error(message, title),
    info: (message, title) => cheer.info(message, title),
    warn: (message, title) => cheer.warning(message, title),
};

export default notifier;
