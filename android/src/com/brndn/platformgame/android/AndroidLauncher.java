package com.brndn.platformgame.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.brndn.platformgame.PlatformGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new PlatformGame(), config);
	}

    @Override
    public void runOnUiThread(Runnable runnable) {

    }

    @Override
    public void startActivity(android.content.Intent intent) {

    }

    @Override
    public android.view.WindowManager getWindowManager() {
        return null;
    }
}
