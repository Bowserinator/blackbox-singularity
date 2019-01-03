package blackbox.game.conversation;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ObjectMap;
import blackbox.game.conversation.graph.*;

/**
 * Contains all the chat nodes and choices
 * for a given screen; you can think of this
 * class as a single "chapter" of the story
 *
 * You should not be manually creating subclasses
 * of this class; use the story compiler tool
 * to generate java code.
 *
 * @author Bowserinator
 */
public abstract class Conversation {
    /**
     * aiData    - See AiChatData class, automatically loaded
     * screen    - The Screen the conversation belongs to
     *
     * currentChatNode   - Currently selected chat node, must have reference in chatNodes
     * title             - Title of conversation, see the story compiler wiki
     * subtitle          - Subtitle of conversation, see the story compiler wiki
     * backgroundId      - Id of background to use, see story compiler wiki
     *
     * variables   - Map of variables to this story only (Auto-generated by compiler)
     * sounds      - Short sound effects this story uses (Auto-generated by compiler)
     * music       - Music this story uses (Auto-generated by compiler)
     *
     * chatNodes   - Map of nodeId: chatNode
     */
    public AiChatData aiData;
    private Screen screen;

    public ChatNode currentChatNode;
    private String title;
    private String subtitle;
    public String backgroundId;

    public ObjectMap<String, Object> variables;
    public ObjectMap<String, Sound> sounds;
    public ObjectMap<String, Music> music;

    public ObjectMap<String, ChatNode> chatNodes;

    /**
     * Construct a new Conversation object
     *
     * @param screen       Screen the conversation belongs to
     * @param title        Title of conversation
     * @param subtitle     Subtitle of conversation
     * @param backgroundId Id of background
     */
    public Conversation(Screen screen, String title, String subtitle, String backgroundId) {
        this.screen = screen;
        this.title = title;
        this.subtitle = subtitle;
        this.backgroundId = backgroundId;

        this.variables = new ObjectMap<String, Object>();
        this.sounds = new ObjectMap<String, Sound>();
        this.music = new ObjectMap<String, Music>();
    }

    /**
     * Define which chat node to go to
     * when the scene is loaded. Use gotoChatNode
     * when overriding the method
     */
    public abstract void gotoStart();

    /**
     * Sets the next scene ID to go to after
     * the scene ends.
     * @param nextSceneId Id of next scene
     */
    public void end(String nextSceneId) {
        // TODO implement
    }

    /***
     * Loads a custom program to display onto
     * the screen
     * @param id ID of program
     */
    public void loadProgramToScreen(String id) {
        // TODO
    }

    /**
     * Sets new chat node ID to display
     * @param id Id of chat node
     */
    public void gotoChatNode(String id) {
        currentChatNode = chatNodes.get(id);
        currentChatNode.onLoad(this);
    }

    /**
     * Returns the screen the conversation belongs to
     * @return Screen
     */
    public Screen getScreen() {
        return screen;
    }

    /**
     * Returns the title of conversation
     * @return Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the subtitle of conversation
     * @return Subtitle
     */
    public String getSubtitle() {
        return subtitle;
    }
}
