package com.smartthings.ratpack.main;


import com.smartthings.behaviour.modules.DimmerLightModule;
import com.smartthings.ratpack.handlers.DimmerLightGetHandler;
import com.smartthings.ratpack.handlers.DimmerLightPostHandler;

import ratpack.guice.Guice;
import ratpack.handling.Chain;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

public class DimmerApp {

  public static void main(String[] args) throws Exception {
    callDimmerSmartApp();
  }
  
  public static void callDimmerSmartApp() throws Exception {
	  RatpackServer.start(s -> s
		        .serverConfig(c -> c.baseDir(BaseDir.find()))
		        .registry(Guice.registry(b -> b.module(DimmerLightModule.class)))
		        .handlers(chain -> chain
		        	.get("switches",DimmerLightGetHandler.class)
		        	.post("setLevel", DimmerLightPostHandler.class)
		            .prefix("static", nested -> nested.fileSystem("assets/images", Chain::files)) // Bind the /static app path to the src/ratpack/assets/images dir
		            .all(ctx -> ctx.render("completed successfully"))
		        )
		    );
	 
  
}
  
}
