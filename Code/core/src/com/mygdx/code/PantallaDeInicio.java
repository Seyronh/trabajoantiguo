package com.mygdx.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

	public class PantallaDeInicio implements Screen {
		
		OrthographicCamera camera;

		public PantallaDeInicio(final Drop game) {
			
			camera = new OrthographicCamera();
			camera.setToOrtho(false, 800, 480);
		}


	        //...Rest of class omitted for succinctness.

	}

}
