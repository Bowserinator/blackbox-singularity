package blackbox.game.scenes;

import blackbox.game.*;
import blackbox.game.graphics.scenes.OfficeNormalBackgroundScene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.Window;

public class TestScreen implements Screen {
    final BlackboxGame game;
    OrthographicCamera camera;

    SpriteBatch batch;
    Texture img;
    TextureRegion background;

    public Music backgroundMusic;

    private OfficeNormalBackgroundScene scene;

    /* Config for menu */
    private int textLeft, textTop, menuShift, menuSpacing;

    public TestScreen(final BlackboxGame game) {
        this.game = game;

        batch = new SpriteBatch();
        scene = new OfficeNormalBackgroundScene(game);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        /* Load music */
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/song1.wav"));

        /* Text spacing */
        textLeft = Config.WINDOW_WIDTH / 30;
        textTop = (int)(Config.WINDOW_HEIGHT * 0.7) - BlackboxGame.fontSizes[0];
        menuShift = BlackboxGame.fontSizes[0] * 2 + (int)(Config.WINDOW_HEIGHT * 0.06);
        menuSpacing = BlackboxGame.fontSizes[2] * 2;

        scene.typeText("Hellomouse OS v1.2.3\n Totally not a fake readme There is text here! You should read it!", 20); // 20

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // TODO replace background with class


        batch.begin();
        batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        scene.render(delta, batch, game);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            //
            scene.scroll -= 20;
        } if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            scene.scroll += 20;
        }

        /* Render title and options */
        /*game.robotoLightFont.get("title2").draw(batch, "BLACKBOX", textLeft, textTop);
        game.robotoLightFont.get("title1").draw(batch, "SINGULARITY", textLeft, textTop - BlackboxGame.fontSizes[0]);

        game.robotoLightFont.get("normal").draw(batch, "New Game", textLeft, textTop - menuShift);
        game.robotoLightFont.get("normal").draw(batch, "Continue Game", textLeft, textTop - menuShift - menuSpacing);
        game.robotoLightFont.get("normal").draw(batch, "Settings", textLeft, textTop - menuShift - menuSpacing * 2);
        game.robotoLightFont.get("normal").draw(batch, "Extras", textLeft, textTop - menuShift - menuSpacing * 3);
        game.robotoLightFont.get("normal").draw(batch, "Credits", textLeft, textTop - menuShift - menuSpacing * 4);
        game.robotoLightFont.get("normal").draw(batch, "Quit Game", textLeft, textTop - menuShift - menuSpacing * 5);

        game.robotoLightFont.get("small").draw(batch, Config.MENU_NOTE, Config.WINDOW_WIDTH - 700, 100);*/

        batch.end();

        /*if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }*/
    }

    @Override
    public void resize(int width, int height) {
        game.generateFonts();
    }

    @Override
    public void show() {
        /* Start playing background music
         * when TestScreen is loaded */
        //backgroundMusic.play();
        //backgroundMusic.setLooping(true);
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
        backgroundMusic.pause();
    }

    @Override
    public void resume() {
        //backgroundMusic.play();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        backgroundMusic.dispose();
    }
}
