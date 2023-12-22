// Retrieve the stored timestamp from local storage
var lastDisableTimestamp = localStorage.getItem("lastDisableTimestamp");

var resendOtpLink = document.getElementById("resendOtpLink");
var resendTimeout = null;

function disableResendButton() {
	resendOtpLink.style.pointerEvents = "none"; // Disable link click
	resendOtpLink.style.background = "#8e918d";
	localStorage.setItem("lastDisableTimestamp", Date.now()); // Store the current timestamp
	resendTimeout = setTimeout(enableResendButton, 120000); // Enable link after 2 minutes (2 * 60 * 1000 milliseconds)
}

function enableResendButton() {
	resendOtpLink.style.pointerEvents = "auto"; // Enable link click
	resendOtpLink.style.background = "#287ad1"; // Reset link color
	localStorage.removeItem("lastDisableTimestamp"); // Remove the stored timestamp
	clearTimeout(resendTimeout); // Clear the timeout
}

disableResendButton();
setTimeout(enableResendButton, 120000);
// Check if the button should still be disabled
if (lastDisableTimestamp) {
	var currentTime = Date.now();
	var timeSinceDisable = currentTime - lastDisableTimestamp;
	if (timeSinceDisable < 120000) {
		disableResendButton();
		setTimeout(enableResendButton, 120000 - timeSinceDisable); // Enable button after remaining time
	}
}

// Attach the disableResendButton function to the click event of the link
resendOtpLink.addEventListener("click", function(event) {
	event.preventDefault(); // Prevent default link behavior
	disableResendButton(); // Call the function to disable the link
});
