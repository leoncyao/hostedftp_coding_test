window.onload = function() {
    const form = document.getElementById("MyForm");
    form.addEventListener("submit", async (event) => {
        event.preventDefault(); // Prevent the default form submission

        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;

        try {
            const hashedPassword = await sha256(password);
            document.getElementById("password").value = hashedPassword;
            // Now you can submit the form programmatically
            form.submit();
        } catch (error) {
            console.error(error);
        }
    });

    // Example SHA-256 hashing function
    async function sha256(str) {
        const crypto = window.crypto || window.msCrypto;
        const buffer = new TextEncoder().encode(str);
        const hashBuffer = await crypto.subtle.digest('SHA-256', buffer);
        const hashArray = Array.from(new Uint8Array(hashBuffer));
        const hashHex = hashArray.map(byte => byte.toString(16).padStart(2, '0')).join('');
        return hashHex;
    }
};