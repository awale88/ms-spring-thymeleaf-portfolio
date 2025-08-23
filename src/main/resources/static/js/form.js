
function showSuccessMessage() {
    Swal.fire({
        title: "Thank you!",
        text: "Your message has been sent successfully.",
        icon: "success",
        confirmButtonText: "OK"
    });
}

function showErrorMessage() {
    Swal.fire({
        title: "Oops!",
        text: "Something went wrong. Please try again.",
        icon: "error",
        confirmButtonText: "OK"
    });
}
