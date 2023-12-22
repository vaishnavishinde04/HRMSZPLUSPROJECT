const audioPlayer = document.getElementById('audioPlayer');
    const playCheckbox = document.getElementById('playCheckbox');

    // Check if user's preference is stored in local storage
    const playbackPreference = localStorage.getItem('playbackPreference');

    // Initialize checkbox based on stored preference
    playCheckbox.checked = playbackPreference === 'true';

    // Function to toggle audio playback
    function togglePlayback() {
        if (playCheckbox.checked) {
            audioPlayer.play();
            localStorage.setItem('playbackPreference', 'true'); // Store preference
        } else {
            audioPlayer.pause();
            localStorage.setItem('playbackPreference', 'false'); // Store preference
        }
    }

    // Playback based on stored preference when page loads
    if (playbackPreference === 'true') {
        audioPlayer.play().catch(error => {
            // Autoplay might be blocked, handle the error
            console.error('Autoplay prevented:', error);
        });
    }

    