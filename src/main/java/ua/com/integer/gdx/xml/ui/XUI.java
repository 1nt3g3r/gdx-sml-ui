package ua.com.integer.gdx.xml.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ObjectMap;

import ua.com.integer.gdx.xml.ui.creator.*;
import ua.com.integer.gdx.xml.ui.element.XUIElement;
import ua.com.integer.gdx.xml.ui.element.XUIElementInflater;
import ua.com.integer.gdx.xml.ui.element.XUIElementLoader;
import ua.com.integer.gdx.xml.ui.res.AssetProvider;
import ua.com.integer.gdx.xml.ui.res.XUIAssets;
import ua.com.integer.gdx.xml.ui.res.XUIVariables;
import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;
import ua.com.integer.gdx.xml.ui.res.parser.XUIAssetParser;
import ua.com.integer.gdx.xml.ui.util.XUIElementUnwrapper;

/**
 * Facade to convenient work
 */
public class XUI {
    private static final XUI instance = new XUI();

    private static String workingDirectory = "xui";
    private ObjectMap<String, XUIElement> elements = new ObjectMap<>();

    private XUIVariables variables = new XUIVariables();

    private XUIAssets assets;

    private XUI() {
    }

    /**
     * Call this method first to init XUI
     */
    public static void init() {
        variables().clear();
        XUICreator.init();
        XUIProcessor.init();

        instance.assets = new XUIAssets();
        instance.elements.clear();
    }

    /**
     * Sets directory with element definition files
     */
    public static void setWorkingDirectory(String workingDir) {
        workingDirectory = workingDir;
    }

    /**
     * Returns directory with element definition files
     * @return
     */
    public static String getWorkingDirectory() {
        return workingDirectory;
    }

    /**
     * Loads and returns {@link XUIElement} from internal {@link com.badlogic.gdx.files.FileHandle} that located in working directory
     * @param name should be without .xml suffix
     */
    public static XUIElement get(String name) {
        checkIsInitialized();

        if (!instance.elements.containsKey(name)) {
            instance.loadElement(name);
        }

        return instance.elements.get(name).copy();
    }

    /**
     * Inflates element with given name into group
     * @param name should be without .xml suffix
     */
    public static void inflate(String name, Group group) {
        checkIsInitialized();

        XUIElement element = instance.get(name);
        XUIElementInflater.inflate(element, group);
    }

    /**
     * Inflates element with given name into stage
     * @param name should be without .xml suffix
     */
    public static void inflate(String name, Stage stage) {
        checkIsInitialized();

        XUIElement element = instance.get(name);
        XUIElementInflater.inflate(element, stage);
    }

    private void loadElement(String name) {
        XUIElement def = XUIElementLoader.load(Gdx.files.internal(workingDirectory + "/" + name + ".xml"));
        XUIElementUnwrapper.unwrap(def);
        elements.put(name, def);
    }

    /**
     * Provides convenient access to {@link XUIAssets}
     */
    public static final XUIAssets assets() {
        checkIsInitialized();
        return instance.assets;
    }

    /**
     * Provides convenient access to {@link XUIVariables}
     */
    public static XUIVariables variables() {
        checkIsInitialized();
        return instance.variables;
    }

    /**
     * Register {@link XUICreator} for given actor type.
     */
    public static void registerXUICreator(String type, XUICreator creator) {
        checkIsInitialized();
        XUICreator.register(type, creator);
    }

    /**
     * Register list of {@link XUIProcessor} for given actor type
     */
    public static void registerXUIProcessors(String type, XUIProcessor... processors) {
        checkIsInitialized();
        XUIProcessor.registerProcessors(type, processors);
    }

    /**
     * Register {@link AssetProvider} to provide asset of <b>assetType</b>
     */
    public static void registerXUIAssetProvider(Class<? extends Object> assetType, AssetProvider assetProvider) {
        checkIsInitialized();
        assets().registerAssetProvider(assetType, assetProvider);
    }

    /**
     * Register {@link XUIAssetParser} to add ability to parse assets of <b>assetType</b> type
     */
    public static void registerXUIAssetParser(String assetType, XUIAssetParser assetParser) {
        checkIsInitialized();
        assets().registerAssetParser(assetType, assetParser);
    }

    private static void checkIsInitialized() {
        if (instance == null) {
            throw new IllegalStateException("Call XUI.init() before any usage");
        }
    }
}
