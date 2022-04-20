package root.controller;

import java.util.List;
import java.util.Set;

import javafx.scene.control.TreeItem;
import root.model.Album;
import root.model.SoundClip;
import root.model.SoundClipBlockingQueue;
import root.model.SoundClipLoader;
import root.model.SoundClipPlayer;
import root.view.MusicOrganizerWindow;

public class MusicOrganizerController {

	private MusicOrganizerWindow view;
	private final SoundClipBlockingQueue queue;
	private final Album root;
	
	public MusicOrganizerController() {

		// TODO: Create the root album for all sound clips
		root = new Album("All Sound Clips");
		
		// Create the blocking queue
		queue = new SoundClipBlockingQueue();
				
		// Create a separate thread for the sound clip player and start it
		
		(new Thread(new SoundClipPlayer(queue))).start();
	}
	
	/**
	 * Load the sound clips found in all subfolders of a path on disk. If path is not
	 * an actual folder on disk, has no effect.
	 */
	public Set<SoundClip> loadSoundClips(String path) {
		Set<SoundClip> clips = SoundClipLoader.loadSoundClips(path);

		// TODO: Add the loaded sound clips to the root album
		clips.forEach(root::addSong);
		return clips;
	}
	
	public void registerView(MusicOrganizerWindow view) {
		this.view = view;
	}
	
	/**
	 * Returns the root album
	 */
	public Album getRootAlbum(){
		return root;
	}
	
	/**
	 * Adds an album to the Music Organizer
	 */
	public void addNewAlbum(){ //TODO Update parameters if needed - e.g. you might want to give the currently selected album as parameter
		// TODO: Add your code here
		final var newAlbumName = view.promptForAlbumName();
		final var newAlbum = new Album(newAlbumName, view.getSelectedAlbum());
		view.getSelectedAlbum().addAlbum(newAlbum);
		view.onAlbumAdded(newAlbum);
	}
	
	/**
	 * Removes an album from the Music Organizer
	 */
	public void deleteAlbum(){ //TODO Update parameters if needed
		// TODO: Add your code here
		view.getSelectedAlbum().setParent(null);
		view.getSelectedAlbum().getParent().removeAlbum(view.getSelectedAlbum());
		view.onAlbumRemoved();
	}
	
	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips(){ //TODO Update parameters if needed
		// TODO: Add your code here
		view.getSelectedSoundClips().forEach(view.getSelectedAlbum()::addSong);
		view.onClipsUpdated();
	}
	
	/**
	 * Removes sound clips from an album
	 */
	public void removeSoundClips(){ //TODO Update parameters if needed
		// TODO: Add your code here
		view.getSelectedSoundClips().forEach(view.getSelectedAlbum()::removeSong);
		view.onClipsUpdated();

	}
	
	/**
	 * Puts the selected sound clips on the queue and lets
	 * the sound clip player thread play them. Essentially, when
	 * this method is called, the selected sound clips in the 
	 * SoundClipTable are played.
	 */
	public void playSoundClips(){
		List<SoundClip> l = view.getSelectedSoundClips();
		queue.enqueue(l);
		for (SoundClip soundClip : l) {
			view.displayMessage("Playing " + soundClip);
		}
	}
}
