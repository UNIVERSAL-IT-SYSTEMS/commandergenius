/*
    SDL - Simple DirectMedia Layer
    Copyright (C) 1997-2011 Sam Lantinga
    Java source code (C) 2009-2011 Sergii Pylypenko

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    There is a license exception for this particular file (Settings.java) -
    you may modify it as you wish without releasing source code,
    as long as the libraries in the binary package you're distributing
    (typically libapplication.so and other closed-source libraries in your .apk file)
    can be linked and executed without error against the SDL compiled
    from the original source code. This implies that you may not modify Java-to-C
    interface, otherwise you have to publish the source code with your changes.

    This exception is here to permit you to replace a rather awkward SDL startup config dialog
    with your own user-friendly version, possibly with lesser options and some help on them.
    Also you may put your own logo in the startup screen instead of the "Powered by SDL" logo.

    You may modify other Java files if needed to implement your custom startup config dialog
    without publishing the source code, to a reasonable extent of course - if your modification
    will make SDL work better, faster or more stable beyond the startup config dialog -
    you still have to publish the source code with such change (and I don't care about
    changes which will make SDL work worse, slower or less stable, you may keep them).
*/

package net.sourceforge.clonekeenplus;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.util.Log;
import java.io.*;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.StatFs;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.Collections;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import java.lang.String;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.widget.TextView;
import android.widget.EditText;
import android.text.Editable;
import android.text.SpannedString;


// TODO: too much code here, split into multiple files, possibly auto-generated menus?
class Settings
{
	static String SettingsFileName = "libsdl-settings.cfg";

	static boolean settingsLoaded = false;
	static boolean settingsChanged = false;
	static final int SETTINGS_FILE_VERSION = 5;

	static void Save(final MainActivity p)
	{
		try {
			ObjectOutputStream out = new ObjectOutputStream(p.openFileOutput( SettingsFileName, p.MODE_WORLD_READABLE ));
			out.writeInt(SETTINGS_FILE_VERSION);
			out.writeBoolean(Globals.DownloadToSdcard);
			out.writeBoolean(Globals.PhoneHasArrowKeys);
			out.writeBoolean(Globals.PhoneHasTrackball);
			out.writeBoolean(Globals.UseAccelerometerAsArrowKeys);
			out.writeBoolean(Globals.UseTouchscreenKeyboard);
			out.writeInt(Globals.TouchscreenKeyboardSize);
			out.writeInt(Globals.AccelerometerSensitivity);
			out.writeInt(Globals.AccelerometerCenterPos);
			out.writeInt(Globals.TrackballDampening);
			out.writeInt(Globals.AudioBufferConfig);
			out.writeInt(Globals.TouchscreenKeyboardTheme);
			out.writeInt(Globals.RightClickMethod);
			out.writeBoolean(Globals.ShowScreenUnderFinger);
			out.writeInt(Globals.LeftClickMethod);
			out.writeBoolean(Globals.MoveMouseWithJoystick);
			out.writeBoolean(Globals.ClickMouseWithDpad);
			out.writeInt(Globals.ClickScreenPressure);
			out.writeInt(Globals.ClickScreenTouchspotSize);
			out.writeBoolean(Globals.KeepAspectRatio);
			out.writeInt(Globals.MoveMouseWithJoystickSpeed);
			out.writeInt(Globals.MoveMouseWithJoystickAccel);
			out.writeInt(SDL_Keys.JAVA_KEYCODE_LAST);
			for( int i = 0; i < SDL_Keys.JAVA_KEYCODE_LAST; i++ )
			{
				out.writeInt(Globals.RemapHwKeycode[i]);
			}
			out.writeInt(Globals.RemapScreenKbKeycode.length);
			for( int i = 0; i < Globals.RemapScreenKbKeycode.length; i++ )
			{
				out.writeInt(Globals.RemapScreenKbKeycode[i]);
			}
			out.writeInt(Globals.ScreenKbControlsShown.length);
			for( int i = 0; i < Globals.ScreenKbControlsShown.length; i++ )
			{
				out.writeBoolean(Globals.ScreenKbControlsShown[i]);
			}
			out.writeInt(Globals.TouchscreenKeyboardTransparency);
			out.writeInt(Globals.RemapMultitouchGestureKeycode.length);
			for( int i = 0; i < Globals.RemapMultitouchGestureKeycode.length; i++ )
			{
				out.writeInt(Globals.RemapMultitouchGestureKeycode[i]);
				out.writeBoolean(Globals.MultitouchGesturesUsed[i]);
			}
			out.writeInt(Globals.MultitouchGestureSensitivity);
			for( int i = 0; i < Globals.TouchscreenCalibration.length; i++ )
				out.writeInt(Globals.TouchscreenCalibration[i]);
			out.writeInt(Globals.DataDir.length());
			for( int i = 0; i < Globals.DataDir.length(); i++ )
				out.writeChar(Globals.DataDir.charAt(i));
			out.writeInt(Globals.CommandLine.length());
			for( int i = 0; i < Globals.CommandLine.length(); i++ )
				out.writeChar(Globals.CommandLine.charAt(i));
			out.writeInt(Globals.ScreenKbControlsLayout.length);
			for( int i = 0; i < Globals.ScreenKbControlsLayout.length; i++ )
				for( int ii = 0; ii < 4; ii++ )
					out.writeInt(Globals.ScreenKbControlsLayout[i][ii]);
			out.writeInt(Globals.LeftClickKey);
			out.writeInt(Globals.RightClickKey);
			out.writeBoolean(Globals.SmoothVideo);
			out.writeInt(Globals.LeftClickTimeout);
			out.writeInt(Globals.RightClickTimeout);
			out.writeBoolean(Globals.RelativeMouseMovement);
			out.writeInt(Globals.RelativeMouseMovementSpeed);
			out.writeInt(Globals.RelativeMouseMovementAccel);
			out.writeBoolean(Globals.MultiThreadedVideo);

			out.writeInt(Globals.OptionalDataDownload.length);
			for(int i = 0; i < Globals.OptionalDataDownload.length; i++)
				out.writeBoolean(Globals.OptionalDataDownload[i]);

			out.close();
			settingsLoaded = true;
			
		} catch( FileNotFoundException e ) {
		} catch( SecurityException e ) {
		} catch ( IOException e ) {};
	}

	static void Load( final MainActivity p )
	{
		if(settingsLoaded) // Prevent starting twice
		{
			return;
		}
		System.out.println("libSDL: Settings.Load(): enter");
		nativeInitKeymap();
		for( int i = 0; i < SDL_Keys.JAVA_KEYCODE_LAST; i++ )
		{
			int sdlKey = nativeGetKeymapKey(i);
			int idx = 0;
			for(int ii = 0; ii < SDL_Keys.values.length; ii++)
				if(SDL_Keys.values[ii] == sdlKey)
					idx = ii;
			Globals.RemapHwKeycode[i] = idx;
		}
		for( int i = 0; i < Globals.RemapScreenKbKeycode.length; i++ )
		{
			int sdlKey = nativeGetKeymapKeyScreenKb(i);
			int idx = 0;
			for(int ii = 0; ii < SDL_Keys.values.length; ii++)
				if(SDL_Keys.values[ii] == sdlKey)
					idx = ii;
			Globals.RemapScreenKbKeycode[i] = idx;
		}
		Globals.ScreenKbControlsShown[0] = Globals.AppNeedsArrowKeys;
		Globals.ScreenKbControlsShown[1] = Globals.AppNeedsTextInput;
		for( int i = 2; i < Globals.ScreenKbControlsShown.length; i++ )
			Globals.ScreenKbControlsShown[i] = ( i - 2 < Globals.AppTouchscreenKeyboardKeysAmount );
		for( int i = 0; i < Globals.RemapMultitouchGestureKeycode.length; i++ )
		{
			int sdlKey = nativeGetKeymapKeyMultitouchGesture(i);
			int idx = 0;
			for(int ii = 0; ii < SDL_Keys.values.length; ii++)
				if(SDL_Keys.values[ii] == sdlKey)
					idx = ii;
			Globals.RemapMultitouchGestureKeycode[i] = idx;
		}
		for( int i = 0; i < Globals.MultitouchGesturesUsed.length; i++ )
			Globals.MultitouchGesturesUsed[i] = true;

		try {
			ObjectInputStream settingsFile = new ObjectInputStream(new FileInputStream( p.getFilesDir().getAbsolutePath() + "/" + SettingsFileName ));
			if( settingsFile.readInt() != SETTINGS_FILE_VERSION )
				throw new IOException();
			Globals.DownloadToSdcard = settingsFile.readBoolean();
			Globals.PhoneHasArrowKeys = settingsFile.readBoolean();
			Globals.PhoneHasTrackball = settingsFile.readBoolean();
			Globals.UseAccelerometerAsArrowKeys = settingsFile.readBoolean();
			Globals.UseTouchscreenKeyboard = settingsFile.readBoolean();
			Globals.TouchscreenKeyboardSize = settingsFile.readInt();
			Globals.AccelerometerSensitivity = settingsFile.readInt();
			Globals.AccelerometerCenterPos = settingsFile.readInt();
			Globals.TrackballDampening = settingsFile.readInt();
			Globals.AudioBufferConfig = settingsFile.readInt();
			Globals.TouchscreenKeyboardTheme = settingsFile.readInt();
			Globals.RightClickMethod = settingsFile.readInt();
			Globals.ShowScreenUnderFinger = settingsFile.readBoolean();
			Globals.LeftClickMethod = settingsFile.readInt();
			Globals.MoveMouseWithJoystick = settingsFile.readBoolean();
			Globals.ClickMouseWithDpad = settingsFile.readBoolean();
			Globals.ClickScreenPressure = settingsFile.readInt();
			Globals.ClickScreenTouchspotSize = settingsFile.readInt();
			Globals.KeepAspectRatio = settingsFile.readBoolean();
			Globals.MoveMouseWithJoystickSpeed = settingsFile.readInt();
			Globals.MoveMouseWithJoystickAccel = settingsFile.readInt();
			int readKeys = settingsFile.readInt();
			for( int i = 0; i < readKeys; i++ )
			{
				Globals.RemapHwKeycode[i] = settingsFile.readInt();
			}
			if( settingsFile.readInt() != Globals.RemapScreenKbKeycode.length )
				throw new IOException();
			for( int i = 0; i < Globals.RemapScreenKbKeycode.length; i++ )
			{
				Globals.RemapScreenKbKeycode[i] = settingsFile.readInt();
			}
			if( settingsFile.readInt() != Globals.ScreenKbControlsShown.length )
				throw new IOException();
			for( int i = 0; i < Globals.ScreenKbControlsShown.length; i++ )
			{
				Globals.ScreenKbControlsShown[i] = settingsFile.readBoolean();
			}
			Globals.TouchscreenKeyboardTransparency = settingsFile.readInt();
			if( settingsFile.readInt() != Globals.RemapMultitouchGestureKeycode.length )
				throw new IOException();
			for( int i = 0; i < Globals.RemapMultitouchGestureKeycode.length; i++ )
			{
				Globals.RemapMultitouchGestureKeycode[i] = settingsFile.readInt();
				Globals.MultitouchGesturesUsed[i] = settingsFile.readBoolean();
			}
			Globals.MultitouchGestureSensitivity = settingsFile.readInt();
			for( int i = 0; i < Globals.TouchscreenCalibration.length; i++ )
				Globals.TouchscreenCalibration[i] = settingsFile.readInt();
			StringBuilder b = new StringBuilder();
			int len = settingsFile.readInt();
			for( int i = 0; i < len; i++ )
				b.append( settingsFile.readChar() );
			Globals.DataDir = b.toString();

			b = new StringBuilder();
			len = settingsFile.readInt();
			for( int i = 0; i < len; i++ )
				b.append( settingsFile.readChar() );
			Globals.CommandLine = b.toString();

			if( settingsFile.readInt() != Globals.ScreenKbControlsLayout.length )
				throw new IOException();
			for( int i = 0; i < Globals.ScreenKbControlsLayout.length; i++ )
				for( int ii = 0; ii < 4; ii++ )
					Globals.ScreenKbControlsLayout[i][ii] = settingsFile.readInt();
			Globals.LeftClickKey = settingsFile.readInt();
			Globals.RightClickKey = settingsFile.readInt();
			Globals.SmoothVideo = settingsFile.readBoolean();
			Globals.LeftClickTimeout = settingsFile.readInt();
			Globals.RightClickTimeout = settingsFile.readInt();
			Globals.RelativeMouseMovement = settingsFile.readBoolean();
			Globals.RelativeMouseMovementSpeed = settingsFile.readInt();
			Globals.RelativeMouseMovementAccel = settingsFile.readInt();
			Globals.MultiThreadedVideo = settingsFile.readBoolean();

			Globals.OptionalDataDownload = new boolean[settingsFile.readInt()];
			for(int i = 0; i < Globals.OptionalDataDownload.length; i++)
				Globals.OptionalDataDownload[i] = settingsFile.readBoolean();
			
			settingsLoaded = true;

			System.out.println("libSDL: Settings.Load(): loaded settings successfully");
			
			return;
			
		} catch( FileNotFoundException e ) {
		} catch( SecurityException e ) {
		} catch ( IOException e ) {};
		
		if( Globals.DataDir.length() == 0 )
			Globals.DataDir = Globals.DownloadToSdcard ?
								Environment.getExternalStorageDirectory().getAbsolutePath() + "/app-data/" + Globals.class.getPackage().getName() :
								p.getFilesDir().getAbsolutePath();
		
		// This code fails for both of my phones!
		/*
		Configuration c = new Configuration();
		c.setToDefaults();
		
		if( c.navigation == Configuration.NAVIGATION_TRACKBALL || 
			c.navigation == Configuration.NAVIGATION_DPAD ||
			c.navigation == Configuration.NAVIGATION_WHEEL )
		{
			Globals.AppNeedsArrowKeys = false;
		}
		
		System.out.println( "libSDL: Phone keypad type: " + 
				(
				c.navigation == Configuration.NAVIGATION_TRACKBALL ? "Trackball" :
				c.navigation == Configuration.NAVIGATION_DPAD ? "Dpad" :
				c.navigation == Configuration.NAVIGATION_WHEEL ? "Wheel" :
				c.navigation == Configuration.NAVIGATION_NONAV ? "None" :
				"Unknown" ) );
		*/

		System.out.println("libSDL: Settings.Load(): loading settings failed, running config dialog");
		p.setUpStatusLabel();
		showConfig(p, true);
	}

	// ===============================================================================================

	public static abstract class Menu
	{
		// Should be overridden by children
		abstract void run(final MainActivity p);
		abstract String title(final MainActivity p);
		boolean enabled()
		{
			return true;
		}
		// Should not be overridden
		boolean enabledOrHidden()
		{
			for( Menu m: Globals.HiddenMenuOptions )
			{
				if( m.getClass().getName().equals( this.getClass().getName() ) )
					return false;
			}
			return enabled();
		}
		void showMenuOptionsList(final MainActivity p, final Menu[] list)
		{
			menuStack.add(this);
			ArrayList<CharSequence> items = new ArrayList<CharSequence> ();
			for( Menu m: list )
			{
				if(m.enabledOrHidden())
					items.add(m.title(p));
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(title(p));
			//builder.setSingleChoiceItems(items.toArray(new CharSequence[0]), MainMenuLastSelected, new DialogInterface.OnClickListener() 
			builder.setItems(items.toArray(new CharSequence[0]), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					dialog.dismiss();
					int selected = 0;

					for( Menu m: list )
					{
						if(m.enabledOrHidden())
						{
							if( selected == item )
							{
								m.run(p);
								return;
							}
							selected ++;
						}
					}
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBackOuterMenu(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}

	static ArrayList<Menu> menuStack = new ArrayList<Menu> ();

	public static void showConfig(final MainActivity p, boolean firstStart)
	{
		settingsChanged = true;

		if( Globals.OptionalDataDownload == null )
		{
			String downloads[] = Globals.DataDownloadUrl.split("\\^");
			Globals.OptionalDataDownload = new boolean[downloads.length];
			boolean oldFormat = true;
			for( int i = 0; i < downloads.length; i++ )
			{
				if( downloads[i].indexOf("!") == 0 )
				{
					Globals.OptionalDataDownload[i] = true;
					oldFormat = false;
				}
			}
			if( oldFormat )
				Globals.OptionalDataDownload[0] = true;
		}

		if(!firstStart)
			new MainMenu().run(p);
		else
		{
			if( Globals.StartupMenuButtonTimeout > 0 ) // If we did not disable startup menu altogether
			{
				for( Menu m: Globals.FirstStartMenuOptions )
					menuStack.add(m);
			}
			goBack(p);
		}
	}

	static void goBack(final MainActivity p)
	{
		if(menuStack.isEmpty())
		{
			Save(p);
			p.startDownloader();
		}
		else
		{
			Menu c = menuStack.remove(menuStack.size() - 1);
			c.run(p);
		}
	}

	static void goBackOuterMenu(final MainActivity p)
	{
		if(!menuStack.isEmpty())
			menuStack.remove(menuStack.size() - 1);
		goBack(p);
	}
	
	static class OkButton extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.ok);
		}
		void run (final MainActivity p)
		{
			goBackOuterMenu(p);
		}
	}

	static class DummyMenu extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.ok);
		}
		void run (final MainActivity p)
		{
			goBack(p);
		}
	}

	static class MainMenu extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.device_config);
		}
		void run (final MainActivity p)
		{
			Menu options[] =
			{
				new DownloadConfig(),
				new OptionalDownloadConfig(false),
				new AdditionalInputConfig(),
				new KeyboardConfigMainMenu(),
				new MouseConfigMainMenu(),
				new ArrowKeysConfig(),
				new AccelerometerConfig(),
				new AudioConfig(),
				new RemapHwKeysConfig(),
				new ScreenGesturesConfig(),
				new VideoSettingsConfig(),
				new OkButton(),
			};
			showMenuOptionsList(p, options);
		}
	}

	static class MouseConfigMainMenu extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.mouse_emulation);
		}
		boolean enabled()
		{
			return Globals.AppUsesMouse;
		}
		void run (final MainActivity p)
		{
			Menu options[] =
			{
				new DisplaySizeConfig(false),
				new LeftClickConfig(),
				new RightClickConfig(),
				new AdditionalMouseConfig(),
				new JoystickMouseConfig(),
				new TouchPressureMeasurementTool(),
				new CalibrateTouchscreenMenu(),
				new OkButton(),
			};
			showMenuOptionsList(p, options);
		}
	}

	static class KeyboardConfigMainMenu extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.controls_screenkb);
		}
		boolean enabled()
		{
			return Globals.UseTouchscreenKeyboard;
		}
		void run (final MainActivity p)
		{
			Menu options[] =
			{
				new ScreenKeyboardThemeConfig(),
				new ScreenKeyboardSizeConfig(),
				new ScreenKeyboardTransparencyConfig(),
				new RemapScreenKbConfig(),
				new CustomizeScreenKbLayout(),
				new OkButton(),
			};
			showMenuOptionsList(p, options);
		}
	}

	static class DownloadConfig extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.storage_question);
		}
		void run (final MainActivity p)
		{
			long freeSdcard = 0;
			long freePhone = 0;
			try
			{
				StatFs sdcard = new StatFs(Environment.getExternalStorageDirectory().getPath());
				StatFs phone = new StatFs(Environment.getDataDirectory().getPath());
				freeSdcard = (long)sdcard.getAvailableBlocks() * sdcard.getBlockSize() / 1024 / 1024;
				freePhone = (long)phone.getAvailableBlocks() * phone.getBlockSize() / 1024 / 1024;
			}
			catch(Exception e) {}

			final CharSequence[] items = { p.getResources().getString(R.string.storage_phone, freePhone),
											p.getResources().getString(R.string.storage_sd, freeSdcard),
											p.getResources().getString(R.string.storage_custom) };
			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(p.getResources().getString(R.string.storage_question));
			builder.setSingleChoiceItems(items, Globals.DownloadToSdcard ? 1 : 0, new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					dialog.dismiss();

					if( item == 2 )
						showCustomDownloadDirConfig(p);
					else
					{
						Globals.DownloadToSdcard = (item != 0);
						Globals.DataDir = Globals.DownloadToSdcard ?
										Environment.getExternalStorageDirectory().getAbsolutePath() + "/app-data/" + Globals.class.getPackage().getName() :
										p.getFilesDir().getAbsolutePath();
						goBack(p);
					}
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
		static void showCustomDownloadDirConfig(final MainActivity p)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(p.getResources().getString(R.string.storage_custom));

			final EditText edit = new EditText(p);
			edit.setFocusableInTouchMode(true);
			edit.setFocusable(true);
			edit.setText(Globals.DataDir);
			builder.setView(edit);

			builder.setPositiveButton(p.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.DataDir = edit.getText().toString();
					dialog.dismiss();
					showCommandLineConfig(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
		static void showCommandLineConfig(final MainActivity p)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(p.getResources().getString(R.string.storage_commandline));

			final EditText edit = new EditText(p);
			edit.setFocusableInTouchMode(true);
			edit.setFocusable(true);
			edit.setText(Globals.CommandLine);
			builder.setView(edit);

			builder.setPositiveButton(p.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.CommandLine = edit.getText().toString();
					dialog.dismiss();
					goBack(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}

	static class OptionalDownloadConfig extends Menu
	{
		boolean firstStart = false;
		OptionalDownloadConfig(boolean firstStart)
		{
			this.firstStart = firstStart;
		}
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.downloads);
		}
		void run (final MainActivity p)
		{
			String [] downloadFiles = Globals.DataDownloadUrl.split("\\^");
			
			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(p.getResources().getString(R.string.downloads));

			CharSequence[] items = new CharSequence[downloadFiles.length];
			for(int i = 0; i < downloadFiles.length; i++ )
			{
				items[i] = new String(downloadFiles[i].split("[|]")[0]);
				if( items[i].toString().indexOf("!") == 0 )
					items[i] = items[i].toString().substring(1);
			}

			if( Globals.OptionalDataDownload == null || Globals.OptionalDataDownload.length != items.length )
			{
				Globals.OptionalDataDownload = new boolean[downloadFiles.length];
				boolean oldFormat = true;
				for( int i = 0; i < downloadFiles.length; i++ )
				{
					if( downloadFiles[i].indexOf("!") == 0 )
					{
						Globals.OptionalDataDownload[i] = true;
						oldFormat = false;
					}
				}
				if( oldFormat )
					Globals.OptionalDataDownload[0] = true;
			}

			builder.setMultiChoiceItems(items, Globals.OptionalDataDownload, new DialogInterface.OnMultiChoiceClickListener()
			{
				public void onClick(DialogInterface dialog, int item, boolean isChecked) 
				{
					Globals.OptionalDataDownload[item] = isChecked;
				}
			});
			builder.setPositiveButton(p.getResources().getString(R.string.ok), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					dialog.dismiss();
					goBack(p);
				}
			});
			if( firstStart )
			{
				builder.setNegativeButton(p.getResources().getString(R.string.show_more_options), new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int item) 
					{
						dialog.dismiss();
						menuStack.clear();
						new MainMenu().run(p);
					}
				});
			}
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}
	
	static class AdditionalInputConfig extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.controls_additional);
		}
		void run (final MainActivity p)
		{
			CharSequence[] items = {
				p.getResources().getString(R.string.controls_screenkb),
				p.getResources().getString(R.string.controls_accelnav)
			};

			boolean defaults[] = { 
				Globals.UseTouchscreenKeyboard,
				Globals.UseAccelerometerAsArrowKeys
			};

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(p.getResources().getString(R.string.controls_additional));
			builder.setMultiChoiceItems(items, defaults, new DialogInterface.OnMultiChoiceClickListener() 
			{
				public void onClick(DialogInterface dialog, int item, boolean isChecked) 
				{
					if( item == 0 )
						Globals.UseTouchscreenKeyboard = isChecked;
					if( item == 1 )
						Globals.UseAccelerometerAsArrowKeys = isChecked;
				}
			});
			builder.setPositiveButton(p.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					dialog.dismiss();
					goBack(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}

	static class AccelerometerConfig extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.accel_question);
		}
		boolean enabled()
		{
			return Globals.UseAccelerometerAsArrowKeys || ! Globals.AppHandlesJoystickSensitivity;
		}
		void run (final MainActivity p)
		{
			if( ! Globals.UseAccelerometerAsArrowKeys || Globals.AppHandlesJoystickSensitivity )
			{
				Globals.AccelerometerSensitivity = 2; // Slow, full range
				showAccelerometerCenterConfig(p);
				return;
			}
			
			final CharSequence[] items = { p.getResources().getString(R.string.accel_fast),
											p.getResources().getString(R.string.accel_medium),
											p.getResources().getString(R.string.accel_slow) };

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(R.string.accel_question);
			builder.setSingleChoiceItems(items, Globals.AccelerometerSensitivity, new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.AccelerometerSensitivity = item;

					dialog.dismiss();
					showAccelerometerCenterConfig(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
		static void showAccelerometerCenterConfig(final MainActivity p)
		{
			if( ! Globals.UseAccelerometerAsArrowKeys || Globals.AppHandlesJoystickSensitivity )
			{
				Globals.AccelerometerCenterPos = 2; // Fixed horizontal center position
				goBack(p);
				return;
			}
			
			final CharSequence[] items = { p.getResources().getString(R.string.accel_floating),
											p.getResources().getString(R.string.accel_fixed_start),
											p.getResources().getString(R.string.accel_fixed_horiz) };

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(R.string.accel_question_center);
			builder.setSingleChoiceItems(items, Globals.AccelerometerCenterPos, new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.AccelerometerCenterPos = item;

					dialog.dismiss();
					goBack(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}


	static class ScreenKeyboardSizeConfig extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.controls_screenkb_size);
		}
		void run (final MainActivity p)
		{
			final CharSequence[] items = {	p.getResources().getString(R.string.controls_screenkb_large),
											p.getResources().getString(R.string.controls_screenkb_medium),
											p.getResources().getString(R.string.controls_screenkb_small),
											p.getResources().getString(R.string.controls_screenkb_tiny) };

			for( int i = 0; i < Globals.ScreenKbControlsLayout.length; i++ )
				for( int ii = 0; ii < 4; ii++ )
					Globals.ScreenKbControlsLayout[i][ii] = 0;

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(p.getResources().getString(R.string.controls_screenkb_size));
			builder.setSingleChoiceItems(items, Globals.TouchscreenKeyboardSize, new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.TouchscreenKeyboardSize = item;

					dialog.dismiss();
					goBack(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}

	static class ScreenKeyboardThemeConfig extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.controls_screenkb_theme);
		}
		void run (final MainActivity p)
		{
			final CharSequence[] items = {
				p.getResources().getString(R.string.controls_screenkb_by, "Ultimate Droid", "Sean Stieber"),
				p.getResources().getString(R.string.controls_screenkb_by, "Simple Theme", "Beholder")
				};

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(p.getResources().getString(R.string.controls_screenkb_theme));
			builder.setSingleChoiceItems(items, Globals.TouchscreenKeyboardTheme, new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.TouchscreenKeyboardTheme = item;

					dialog.dismiss();
					goBack(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}

	static class ScreenKeyboardTransparencyConfig extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.controls_screenkb_transparency);
		}
		void run (final MainActivity p)
		{
			final CharSequence[] items = {	p.getResources().getString(R.string.controls_screenkb_trans_0),
											p.getResources().getString(R.string.controls_screenkb_trans_1),
											p.getResources().getString(R.string.controls_screenkb_trans_2),
											p.getResources().getString(R.string.controls_screenkb_trans_3),
											p.getResources().getString(R.string.controls_screenkb_trans_4) };

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(p.getResources().getString(R.string.controls_screenkb_transparency));
			builder.setSingleChoiceItems(items, Globals.TouchscreenKeyboardTransparency, new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.TouchscreenKeyboardTransparency = item;

					dialog.dismiss();
					goBack(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}

	static class AudioConfig extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.audiobuf_question);
		}
		void run (final MainActivity p)
		{
			final CharSequence[] items = {	p.getResources().getString(R.string.audiobuf_verysmall),
											p.getResources().getString(R.string.audiobuf_small),
											p.getResources().getString(R.string.audiobuf_medium),
											p.getResources().getString(R.string.audiobuf_large) };

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(R.string.audiobuf_question);
			builder.setSingleChoiceItems(items, Globals.AudioBufferConfig, new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.AudioBufferConfig = item;
					dialog.dismiss();
					goBack(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}

	static class DisplaySizeConfig extends Menu
	{
		boolean firstStart = false;
		DisplaySizeConfig(boolean firstStart)
		{
			this.firstStart = firstStart;
		}
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.display_size_mouse);
		}
		void run (final MainActivity p)
		{
			CharSequence[] items = {	p.getResources().getString(R.string.display_size_large),
										p.getResources().getString(R.string.display_size_small) };
			if( firstStart )
			{
				CharSequence[] items2 = {	p.getResources().getString(R.string.display_size_large),
											p.getResources().getString(R.string.display_size_small),
											p.getResources().getString(R.string.show_more_options) };
				items = items2;
			}

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(R.string.display_size);
			class ClickListener implements DialogInterface.OnClickListener
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					dialog.dismiss();
					if( item == 0 )
					{
						Globals.LeftClickMethod = Mouse.LEFT_CLICK_WITH_TAP_OR_TIMEOUT;
						Globals.RelativeMouseMovement = false;
						Globals.ShowScreenUnderFinger = false;
					}
					if( item == 1 )
					{
						if( Globals.SwVideoMode )
						{
							Globals.LeftClickMethod = Mouse.LEFT_CLICK_NEAR_CURSOR;
							Globals.RelativeMouseMovement = false;
							Globals.ShowScreenUnderFinger = true;
						}
						else
						{
							// OpenGL does not support magnifying glass
							Globals.LeftClickMethod = Mouse.LEFT_CLICK_WITH_TAP_OR_TIMEOUT;
							Globals.RelativeMouseMovement = true;
							Globals.ShowScreenUnderFinger = false;
						}
					}
					if( item == 2 )
					{
						menuStack.clear();
						new MainMenu().run(p);
					}
					goBack(p);
				}
			}
			if( firstStart )
				builder.setItems(items, new ClickListener());
			else
				builder.setSingleChoiceItems(items,
					(Globals.LeftClickMethod == Mouse.LEFT_CLICK_WITH_TAP_OR_TIMEOUT && ! Globals.RelativeMouseMovement) ? 0 : 1,
					new ClickListener());
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}

	static class LeftClickConfig extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.leftclick_question);
		}
		void run (final MainActivity p)
		{
			final CharSequence[] items = {	p.getResources().getString(R.string.leftclick_normal),
											p.getResources().getString(R.string.leftclick_near_cursor),
											p.getResources().getString(R.string.leftclick_multitouch),
											p.getResources().getString(R.string.leftclick_pressure),
											p.getResources().getString(R.string.rightclick_key),
											p.getResources().getString(R.string.leftclick_timeout),
											p.getResources().getString(R.string.leftclick_tap),
											p.getResources().getString(R.string.leftclick_tap_or_timeout) };

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(R.string.leftclick_question);
			builder.setSingleChoiceItems(items, Globals.LeftClickMethod, new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					dialog.dismiss();
					Globals.LeftClickMethod = item;
					if( item == Mouse.LEFT_CLICK_WITH_KEY )
						p.keyListener = new KeyRemapToolMouseClick(p, true);
					else if( item == Mouse.LEFT_CLICK_WITH_TIMEOUT || item == Mouse.LEFT_CLICK_WITH_TAP_OR_TIMEOUT )
						showLeftClickTimeoutConfig(p);
					else
						goBack(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
		static void showLeftClickTimeoutConfig(final MainActivity p) {
			final CharSequence[] items = {	p.getResources().getString(R.string.leftclick_timeout_time_0),
											p.getResources().getString(R.string.leftclick_timeout_time_1),
											p.getResources().getString(R.string.leftclick_timeout_time_2),
											p.getResources().getString(R.string.leftclick_timeout_time_3),
											p.getResources().getString(R.string.leftclick_timeout_time_4) };

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(R.string.leftclick_timeout_time);
			builder.setSingleChoiceItems(items, Globals.LeftClickTimeout, new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.LeftClickTimeout = item;
					dialog.dismiss();
					goBack(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}

	static class RightClickConfig extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.rightclick_question);
		}
		boolean enabled()
		{
			return Globals.AppNeedsTwoButtonMouse;
		}
		void run (final MainActivity p)
		{
			final CharSequence[] items = {	p.getResources().getString(R.string.rightclick_none),
											p.getResources().getString(R.string.rightclick_multitouch),
											p.getResources().getString(R.string.rightclick_pressure),
											p.getResources().getString(R.string.rightclick_key),
											p.getResources().getString(R.string.leftclick_timeout) };

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(R.string.rightclick_question);
			builder.setSingleChoiceItems(items, Globals.RightClickMethod, new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.RightClickMethod = item;
					dialog.dismiss();
					if( item == Mouse.RIGHT_CLICK_WITH_KEY )
						p.keyListener = new KeyRemapToolMouseClick(p, false);
					else if( item == Mouse.RIGHT_CLICK_WITH_TIMEOUT )
						showRightClickTimeoutConfig(p);
					else
						goBack(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}

		static void showRightClickTimeoutConfig(final MainActivity p) {
			final CharSequence[] items = {	p.getResources().getString(R.string.leftclick_timeout_time_0),
											p.getResources().getString(R.string.leftclick_timeout_time_1),
											p.getResources().getString(R.string.leftclick_timeout_time_2),
											p.getResources().getString(R.string.leftclick_timeout_time_3),
											p.getResources().getString(R.string.leftclick_timeout_time_4) };

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(R.string.leftclick_timeout_time);
			builder.setSingleChoiceItems(items, Globals.RightClickTimeout, new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.RightClickTimeout = item;
					dialog.dismiss();
					goBack(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}

	public static class KeyRemapToolMouseClick implements KeyEventsListener
	{
		MainActivity p;
		boolean leftClick;
		public KeyRemapToolMouseClick(MainActivity _p, boolean leftClick)
		{
			p = _p;
			p.setText(p.getResources().getString(R.string.remap_hwkeys_press));
			this.leftClick = leftClick;
		}
		
		public void onKeyEvent(final int keyCode)
		{
			p.keyListener = null;
			int keyIndex = keyCode;
			if( keyIndex < 0 )
				keyIndex = 0;
			if( keyIndex > SDL_Keys.JAVA_KEYCODE_LAST )
				keyIndex = 0;

			if( leftClick )
				Globals.LeftClickKey = keyIndex;
			else
				Globals.RightClickKey = keyIndex;

			goBack(p);
		}
	}

	static class AdditionalMouseConfig extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.pointandclick_question);
		}
		void run (final MainActivity p)
		{
			CharSequence[] items = {
				p.getResources().getString(R.string.pointandclick_showcreenunderfinger2),
				p.getResources().getString(R.string.pointandclick_joystickmouse),
				p.getResources().getString(R.string.click_with_dpadcenter),
				p.getResources().getString(R.string.pointandclick_relative)
			};

			boolean defaults[] = { 
				Globals.ShowScreenUnderFinger,
				Globals.MoveMouseWithJoystick,
				Globals.ClickMouseWithDpad,
				Globals.RelativeMouseMovement
			};

			
			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(p.getResources().getString(R.string.pointandclick_question));
			builder.setMultiChoiceItems(items, defaults, new DialogInterface.OnMultiChoiceClickListener()
			{
				public void onClick(DialogInterface dialog, int item, boolean isChecked) 
				{
					if( item == 0 )
						Globals.ShowScreenUnderFinger = isChecked;
					if( item == 1 )
						Globals.MoveMouseWithJoystick = isChecked;
					if( item == 2 )
						Globals.ClickMouseWithDpad = isChecked;
					if( item == 3 )
						Globals.RelativeMouseMovement = isChecked;
				}
			});
			builder.setPositiveButton(p.getResources().getString(R.string.ok), new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					dialog.dismiss();
					if( Globals.RelativeMouseMovement )
						showRelativeMouseMovementConfig(p);
					else
						goBack(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}

		static void showRelativeMouseMovementConfig(final MainActivity p)
		{
			final CharSequence[] items = {	p.getResources().getString(R.string.accel_veryslow),
											p.getResources().getString(R.string.accel_slow),
											p.getResources().getString(R.string.accel_medium),
											p.getResources().getString(R.string.accel_fast),
											p.getResources().getString(R.string.accel_veryfast) };

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(R.string.pointandclick_relative_speed);
			builder.setSingleChoiceItems(items, Globals.RelativeMouseMovementSpeed, new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.RelativeMouseMovementSpeed = item;

					dialog.dismiss();
					showRelativeMouseMovementConfig1(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}

		static void showRelativeMouseMovementConfig1(final MainActivity p)
		{
			final CharSequence[] items = {	p.getResources().getString(R.string.none),
											p.getResources().getString(R.string.accel_slow),
											p.getResources().getString(R.string.accel_medium),
											p.getResources().getString(R.string.accel_fast) };

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(R.string.pointandclick_relative_accel);
			builder.setSingleChoiceItems(items, Globals.RelativeMouseMovementAccel, new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.RelativeMouseMovementAccel = item;

					dialog.dismiss();
					goBack(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}


	static class ArrowKeysConfig extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.controls_question);
		}
		boolean enabled()
		{
			return Globals.AppNeedsArrowKeys || Globals.MoveMouseWithJoystick;
		}
		void run (final MainActivity p)
		{
			final CharSequence[] items = { p.getResources().getString(R.string.controls_arrows),
											p.getResources().getString(R.string.controls_trackball),
											p.getResources().getString(R.string.controls_touch) };

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(p.getResources().getString(R.string.controls_question));
			builder.setSingleChoiceItems(items, Globals.PhoneHasArrowKeys ? 0 : ( Globals.PhoneHasTrackball ? 1 : 2 ), new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.PhoneHasArrowKeys = (item == 0);
					Globals.PhoneHasTrackball = (item == 1);

					dialog.dismiss();
					if( Globals.PhoneHasTrackball )
						showTrackballConfig(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}

		static void showTrackballConfig(final MainActivity p)
		{
			final CharSequence[] items = { p.getResources().getString(R.string.trackball_no_dampening),
											p.getResources().getString(R.string.trackball_fast),
											p.getResources().getString(R.string.trackball_medium),
											p.getResources().getString(R.string.trackball_slow) };

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(p.getResources().getString(R.string.trackball_question));
			builder.setSingleChoiceItems(items, Globals.TrackballDampening, new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.TrackballDampening = item;

					dialog.dismiss();
					goBack(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}

	static class JoystickMouseConfig extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.pointandclick_joystickmousespeed);
		}
		boolean enabled()
		{
			return Globals.MoveMouseWithJoystick;
		};
		void run (final MainActivity p)
		{
			final CharSequence[] items = {	p.getResources().getString(R.string.accel_slow),
											p.getResources().getString(R.string.accel_medium),
											p.getResources().getString(R.string.accel_fast) };

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(R.string.pointandclick_joystickmousespeed);
			builder.setSingleChoiceItems(items, Globals.MoveMouseWithJoystickSpeed, new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.MoveMouseWithJoystickSpeed = item;

					dialog.dismiss();
					showJoystickMouseAccelConfig(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}

		static void showJoystickMouseAccelConfig(final MainActivity p)
		{
			final CharSequence[] items = {	p.getResources().getString(R.string.none),
											p.getResources().getString(R.string.accel_slow),
											p.getResources().getString(R.string.accel_medium),
											p.getResources().getString(R.string.accel_fast) };

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(R.string.pointandclick_joystickmouseaccel);
			builder.setSingleChoiceItems(items, Globals.MoveMouseWithJoystickAccel, new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.MoveMouseWithJoystickAccel = item;

					dialog.dismiss();
					goBack(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}

	public interface TouchEventsListener
	{
		public void onTouchEvent(final MotionEvent ev);
	}

	public interface KeyEventsListener
	{
		public void onKeyEvent(final int keyCode);
	}

	static class TouchPressureMeasurementTool extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.measurepressure);
		}
		boolean enabled()
		{
			return Globals.RightClickMethod == Mouse.RIGHT_CLICK_WITH_PRESSURE ||
					Globals.LeftClickMethod == Mouse.LEFT_CLICK_WITH_PRESSURE;
		};
		void run (final MainActivity p)
		{
			p.setText(p.getResources().getString(R.string.measurepressure_touchplease));
			p.touchListener = new TouchMeasurementTool(p);
		}

		public static class TouchMeasurementTool implements TouchEventsListener
		{
			MainActivity p;
			ArrayList<Integer> force = new ArrayList<Integer>();
			ArrayList<Integer> radius = new ArrayList<Integer>();
			static final int maxEventAmount = 100;
			
			public TouchMeasurementTool(MainActivity _p) 
			{
				p = _p;
			}

			public void onTouchEvent(final MotionEvent ev)
			{
				force.add(new Integer((int)(ev.getPressure() * 1000.0)));
				radius.add(new Integer((int)(ev.getSize() * 1000.0)));
				p.setText(p.getResources().getString(R.string.measurepressure_response, force.get(force.size()-1), radius.get(radius.size()-1)));
				try {
					Thread.sleep(10L);
				} catch (InterruptedException e) { }
				
				if( force.size() >= maxEventAmount )
				{
					p.touchListener = null;
					Globals.ClickScreenPressure = getAverageForce();
					Globals.ClickScreenTouchspotSize = getAverageRadius();
					System.out.println("SDL: measured average force " + Globals.ClickScreenPressure + " radius " + Globals.ClickScreenTouchspotSize);
					goBack(p);
				}
			}

			int getAverageForce()
			{
				int avg = 0;
				for(Integer f: force)
				{
					avg += f;
				}
				return avg / force.size();
			}
			int getAverageRadius()
			{
				int avg = 0;
				for(Integer r: radius)
				{
					avg += r;
				}
				return avg / radius.size();
			}
		}
	}
	
	static class RemapHwKeysConfig extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.remap_hwkeys);
		}
		//boolean enabled() { return true; };
		void run (final MainActivity p)
		{
			p.setText(p.getResources().getString(R.string.remap_hwkeys_press));
			p.keyListener = new KeyRemapTool(p);
		}

		public static class KeyRemapTool implements KeyEventsListener
		{
			MainActivity p;
			public KeyRemapTool(MainActivity _p)
			{
				p = _p;
			}
			
			public void onKeyEvent(final int keyCode)
			{
				p.keyListener = null;
				int keyIndex = keyCode;
				if( keyIndex < 0 )
					keyIndex = 0;
				if( keyIndex > SDL_Keys.JAVA_KEYCODE_LAST )
					keyIndex = 0;

				final int KeyIndexFinal = keyIndex;
				AlertDialog.Builder builder = new AlertDialog.Builder(p);
				builder.setTitle(R.string.remap_hwkeys_select);
				builder.setSingleChoiceItems(SDL_Keys.namesSorted, SDL_Keys.namesSortedBackIdx[Globals.RemapHwKeycode[keyIndex]], new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int item)
					{
						Globals.RemapHwKeycode[KeyIndexFinal] = SDL_Keys.namesSortedIdx[item];

						dialog.dismiss();
						goBack(p);
					}
				});
				builder.setOnCancelListener(new DialogInterface.OnCancelListener()
				{
					public void onCancel(DialogInterface dialog)
					{
						goBack(p);
					}
				});
				AlertDialog alert = builder.create();
				alert.setOwnerActivity(p);
				alert.show();
			}
		}
	}

	static class RemapScreenKbConfig extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.remap_screenkb);
		}
		//boolean enabled() { return true; };
		void run (final MainActivity p)
		{
			CharSequence[] items = {
				p.getResources().getString(R.string.remap_screenkb_joystick),
				p.getResources().getString(R.string.remap_screenkb_button_text),
				p.getResources().getString(R.string.remap_screenkb_button) + " 1",
				p.getResources().getString(R.string.remap_screenkb_button) + " 2",
				p.getResources().getString(R.string.remap_screenkb_button) + " 3",
				p.getResources().getString(R.string.remap_screenkb_button) + " 4",
				p.getResources().getString(R.string.remap_screenkb_button) + " 5",
				p.getResources().getString(R.string.remap_screenkb_button) + " 6",
			};

			boolean defaults[] = { 
				Globals.ScreenKbControlsShown[0],
				Globals.ScreenKbControlsShown[1],
				Globals.ScreenKbControlsShown[2],
				Globals.ScreenKbControlsShown[3],
				Globals.ScreenKbControlsShown[4],
				Globals.ScreenKbControlsShown[5],
				Globals.ScreenKbControlsShown[6],
				Globals.ScreenKbControlsShown[7],
			};
			
			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(p.getResources().getString(R.string.remap_screenkb));
			builder.setMultiChoiceItems(items, defaults, new DialogInterface.OnMultiChoiceClickListener() 
			{
				public void onClick(DialogInterface dialog, int item, boolean isChecked) 
				{
					if( ! Globals.UseTouchscreenKeyboard )
						item += 8;
					Globals.ScreenKbControlsShown[item] = isChecked;
				}
			});
			builder.setPositiveButton(p.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					dialog.dismiss();
					showRemapScreenKbConfig2(p, 0);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}

		static void showRemapScreenKbConfig2(final MainActivity p, final int currentButton)
		{
			CharSequence[] items = {
				p.getResources().getString(R.string.remap_screenkb_button) + " 1",
				p.getResources().getString(R.string.remap_screenkb_button) + " 2",
				p.getResources().getString(R.string.remap_screenkb_button) + " 3",
				p.getResources().getString(R.string.remap_screenkb_button) + " 4",
				p.getResources().getString(R.string.remap_screenkb_button) + " 5",
				p.getResources().getString(R.string.remap_screenkb_button) + " 6",
			};
			
			if( currentButton >= Globals.RemapScreenKbKeycode.length )
			{
				goBack(p);
				return;
			}
			if( ! Globals.ScreenKbControlsShown[currentButton + 2] )
			{
				showRemapScreenKbConfig2(p, currentButton + 1);
				return;
			}

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(items[currentButton]);
			builder.setSingleChoiceItems(SDL_Keys.namesSorted, SDL_Keys.namesSortedBackIdx[Globals.RemapScreenKbKeycode[currentButton]], new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					Globals.RemapScreenKbKeycode[currentButton] = SDL_Keys.namesSortedIdx[item];

					dialog.dismiss();
					showRemapScreenKbConfig2(p, currentButton + 1);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}
	
	static class ScreenGesturesConfig extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.remap_screenkb_button_gestures);
		}
		//boolean enabled() { return true; };
		void run (final MainActivity p)
		{
			CharSequence[] items = {
				p.getResources().getString(R.string.remap_screenkb_button_zoomin),
				p.getResources().getString(R.string.remap_screenkb_button_zoomout),
				p.getResources().getString(R.string.remap_screenkb_button_rotateleft),
				p.getResources().getString(R.string.remap_screenkb_button_rotateright),
			};

			boolean defaults[] = { 
				Globals.MultitouchGesturesUsed[0],
				Globals.MultitouchGesturesUsed[1],
				Globals.MultitouchGesturesUsed[2],
				Globals.MultitouchGesturesUsed[3],
			};
			
			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(p.getResources().getString(R.string.remap_screenkb_button_gestures));
			builder.setMultiChoiceItems(items, defaults, new DialogInterface.OnMultiChoiceClickListener() 
			{
				public void onClick(DialogInterface dialog, int item, boolean isChecked) 
				{
					Globals.MultitouchGesturesUsed[item] = isChecked;
				}
			});
			builder.setPositiveButton(p.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					dialog.dismiss();
					showScreenGesturesConfig2(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}

		static void showScreenGesturesConfig2(final MainActivity p)
		{
			final CharSequence[] items = {
				p.getResources().getString(R.string.accel_slow),
				p.getResources().getString(R.string.accel_medium),
				p.getResources().getString(R.string.accel_fast),
				p.getResources().getString(R.string.accel_veryfast)
			};

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(R.string.remap_screenkb_button_gestures_sensitivity);
			builder.setSingleChoiceItems(items, Globals.MultitouchGestureSensitivity, new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					Globals.MultitouchGestureSensitivity = item;

					dialog.dismiss();
					showScreenGesturesConfig3(p, 0);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}

		static void showScreenGesturesConfig3(final MainActivity p, final int currentButton)
		{
			CharSequence[] items = {
				p.getResources().getString(R.string.remap_screenkb_button_zoomin),
				p.getResources().getString(R.string.remap_screenkb_button_zoomout),
				p.getResources().getString(R.string.remap_screenkb_button_rotateleft),
				p.getResources().getString(R.string.remap_screenkb_button_rotateright),
			};
			
			if( currentButton >= Globals.RemapMultitouchGestureKeycode.length )
			{
				goBack(p);
				return;
			}
			if( ! Globals.MultitouchGesturesUsed[currentButton] )
			{
				showScreenGesturesConfig3(p, currentButton + 1);
				return;
			}

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(items[currentButton]);
			builder.setSingleChoiceItems(SDL_Keys.namesSorted, SDL_Keys.namesSortedBackIdx[Globals.RemapMultitouchGestureKeycode[currentButton]], new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int item)
				{
					Globals.RemapMultitouchGestureKeycode[currentButton] = SDL_Keys.namesSortedIdx[item];

					dialog.dismiss();
					showScreenGesturesConfig3(p, currentButton + 1);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}
	
	static class CalibrateTouchscreenMenu extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.calibrate_touchscreen);
		}
		//boolean enabled() { return true; };
		void run (final MainActivity p)
		{
			p.setText(p.getResources().getString(R.string.calibrate_touchscreen_touch));
			Globals.TouchscreenCalibration[0] = 0;
			Globals.TouchscreenCalibration[1] = 0;
			Globals.TouchscreenCalibration[2] = 0;
			Globals.TouchscreenCalibration[3] = 0;
			ScreenEdgesCalibrationTool tool = new ScreenEdgesCalibrationTool(p);
			p.touchListener = tool;
			p.keyListener = tool;
		}

		static class ScreenEdgesCalibrationTool implements TouchEventsListener, KeyEventsListener
		{
			MainActivity p;
			ImageView img;
			Bitmap bmp;
			
			public ScreenEdgesCalibrationTool(MainActivity _p) 
			{
				p = _p;
				img = new ImageView(p);
				img.setLayoutParams(new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
				img.setScaleType(ImageView.ScaleType.MATRIX);
				bmp = BitmapFactory.decodeResource( p.getResources(), R.drawable.calibrate );
				img.setImageBitmap(bmp);
				Matrix m = new Matrix();
				RectF src = new RectF(0, 0, bmp.getWidth(), bmp.getHeight());
				RectF dst = new RectF(Globals.TouchscreenCalibration[0], Globals.TouchscreenCalibration[1], 
										Globals.TouchscreenCalibration[2], Globals.TouchscreenCalibration[3]);
				m.setRectToRect(src, dst, Matrix.ScaleToFit.FILL);
				img.setImageMatrix(m);
				p.getVideoLayout().addView(img);
			}

			public void onTouchEvent(final MotionEvent ev)
			{
				if( Globals.TouchscreenCalibration[0] == Globals.TouchscreenCalibration[1] &&
					Globals.TouchscreenCalibration[1] == Globals.TouchscreenCalibration[2] &&
					Globals.TouchscreenCalibration[2] == Globals.TouchscreenCalibration[3] )
				{
					Globals.TouchscreenCalibration[0] = (int)ev.getX();
					Globals.TouchscreenCalibration[1] = (int)ev.getY();
					Globals.TouchscreenCalibration[2] = (int)ev.getX();
					Globals.TouchscreenCalibration[3] = (int)ev.getY();
				}
				if( ev.getX() < Globals.TouchscreenCalibration[0] )
					Globals.TouchscreenCalibration[0] = (int)ev.getX();
				if( ev.getY() < Globals.TouchscreenCalibration[1] )
					Globals.TouchscreenCalibration[1] = (int)ev.getY();
				if( ev.getX() > Globals.TouchscreenCalibration[2] )
					Globals.TouchscreenCalibration[2] = (int)ev.getX();
				if( ev.getY() > Globals.TouchscreenCalibration[3] )
					Globals.TouchscreenCalibration[3] = (int)ev.getY();
				Matrix m = new Matrix();
				RectF src = new RectF(0, 0, bmp.getWidth(), bmp.getHeight());
				RectF dst = new RectF(Globals.TouchscreenCalibration[0], Globals.TouchscreenCalibration[1], 
										Globals.TouchscreenCalibration[2], Globals.TouchscreenCalibration[3]);
				m.setRectToRect(src, dst, Matrix.ScaleToFit.FILL);
				img.setImageMatrix(m);
			}

			public void onKeyEvent(final int keyCode)
			{
				p.touchListener = null;
				p.keyListener = null;
				p.getVideoLayout().removeView(img);
				goBack(p);
			}
		}
	}

	static class CustomizeScreenKbLayout extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.screenkb_custom_layout);
		}
		//boolean enabled() { return true; };
		void run (final MainActivity p)
		{
			p.setText(p.getResources().getString(R.string.screenkb_custom_layout_help));
			CustomizeScreenKbLayoutTool tool = new CustomizeScreenKbLayoutTool(p);
			p.touchListener = tool;
			p.keyListener = tool;
		}

		static class CustomizeScreenKbLayoutTool implements TouchEventsListener, KeyEventsListener
		{
			MainActivity p;
			FrameLayout layout = null;
			ImageView imgs[] =  new ImageView[Globals.ScreenKbControlsLayout.length];
			Bitmap bmps[] = new Bitmap[Globals.ScreenKbControlsLayout.length];
			int currentButton = 0;
			int buttons[] = {
				R.drawable.dpad,
				R.drawable.keyboard,
				R.drawable.b1,
				R.drawable.b2,
				R.drawable.b3,
				R.drawable.b4,
				R.drawable.b5,
				R.drawable.b6
			};
			
			public CustomizeScreenKbLayoutTool(MainActivity _p) 
			{
				p = _p;
				layout = new FrameLayout(p);
				p.getVideoLayout().addView(layout);
				currentButton = 0;
				setupButton(true);
			}
			
			void setupButton(boolean undo)
			{
				do {
					currentButton += undo ? -1 : 1;
					if(currentButton >= Globals.ScreenKbControlsLayout.length)
					{
						p.getVideoLayout().removeView(layout);
						layout = null;
						p.touchListener = null;
						p.keyListener = null;
						goBack(p);
						return;
					}
					if(currentButton < 0)
					{
						currentButton = 0;
						undo = false;
					}
				} while( ! Globals.ScreenKbControlsShown[currentButton] );
				
				if( imgs[currentButton] == null )
				{
					imgs[currentButton] = new ImageView(p);
					imgs[currentButton].setLayoutParams(new ViewGroup.LayoutParams( ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
					imgs[currentButton].setScaleType(ImageView.ScaleType.MATRIX);
					bmps[currentButton] = BitmapFactory.decodeResource( p.getResources(), buttons[currentButton] );
					imgs[currentButton].setImageBitmap(bmps[currentButton]);
					layout.addView(imgs[currentButton]);
				}
				Matrix m = new Matrix();
				RectF src = new RectF(0, 0, bmps[currentButton].getWidth(), bmps[currentButton].getHeight());
				RectF dst = new RectF(Globals.ScreenKbControlsLayout[currentButton][0], Globals.ScreenKbControlsLayout[currentButton][1],
										Globals.ScreenKbControlsLayout[currentButton][2], Globals.ScreenKbControlsLayout[currentButton][3]);
				m.setRectToRect(src, dst, Matrix.ScaleToFit.FILL);
				imgs[currentButton].setImageMatrix(m);
			}

			public void onTouchEvent(final MotionEvent ev)
			{
				if( ev.getAction() == MotionEvent.ACTION_DOWN )
				{
					Globals.ScreenKbControlsLayout[currentButton][0] = (int)ev.getX();
					Globals.ScreenKbControlsLayout[currentButton][1] = (int)ev.getY();
					Globals.ScreenKbControlsLayout[currentButton][2] = (int)ev.getX();
					Globals.ScreenKbControlsLayout[currentButton][3] = (int)ev.getY();
				}
				if( ev.getAction() == MotionEvent.ACTION_MOVE )
				{
					if( Globals.ScreenKbControlsLayout[currentButton][0] > (int)ev.getX() )
						Globals.ScreenKbControlsLayout[currentButton][0] = (int)ev.getX();
					if( Globals.ScreenKbControlsLayout[currentButton][1] > (int)ev.getY() )
						Globals.ScreenKbControlsLayout[currentButton][1] = (int)ev.getY();
					if( Globals.ScreenKbControlsLayout[currentButton][2] < (int)ev.getX() )
						Globals.ScreenKbControlsLayout[currentButton][2] = (int)ev.getX();
					if( Globals.ScreenKbControlsLayout[currentButton][3] < (int)ev.getY() )
						Globals.ScreenKbControlsLayout[currentButton][3] = (int)ev.getY();
				}

				Matrix m = new Matrix();
				RectF src = new RectF(0, 0, bmps[currentButton].getWidth(), bmps[currentButton].getHeight());
				RectF dst = new RectF(Globals.ScreenKbControlsLayout[currentButton][0], Globals.ScreenKbControlsLayout[currentButton][1],
										Globals.ScreenKbControlsLayout[currentButton][2], Globals.ScreenKbControlsLayout[currentButton][3]);
				m.setRectToRect(src, dst, Matrix.ScaleToFit.FILL);
				imgs[currentButton].setImageMatrix(m);

				if( ev.getAction() == MotionEvent.ACTION_UP )
					setupButton(false);
			}

			public void onKeyEvent(final int keyCode)
			{
				if( layout != null && imgs[currentButton] != null )
					layout.removeView(imgs[currentButton]);
				imgs[currentButton] = null;
				setupButton(true);
			}
		}
	}

	static class VideoSettingsConfig extends Menu
	{
		String title(final MainActivity p)
		{
			return p.getResources().getString(R.string.video);
		}
		//boolean enabled() { return true; };
		void run (final MainActivity p)
		{
			CharSequence[] items = {
				p.getResources().getString(R.string.pointandclick_keepaspectratio),
				p.getResources().getString(R.string.pointandclick_showcreenunderfinger2),
				p.getResources().getString(R.string.video_smooth)
			};
			boolean defaults[] = { 
				Globals.KeepAspectRatio,
				Globals.ShowScreenUnderFinger,
				Globals.SmoothVideo
			};

			if(Globals.SwVideoMode)
			{
				CharSequence[] items2 = {
					p.getResources().getString(R.string.pointandclick_keepaspectratio),
					p.getResources().getString(R.string.pointandclick_showcreenunderfinger2),
					p.getResources().getString(R.string.video_smooth),
					p.getResources().getString(R.string.video_separatethread),
				};
				boolean defaults2[] = { 
					Globals.KeepAspectRatio,
					Globals.ShowScreenUnderFinger,
					Globals.SmoothVideo,
					Globals.MultiThreadedVideo
				};
				items = items2;
				defaults = defaults2;
			}

			if(Globals.Using_SDL_1_3)
			{
				CharSequence[] items2 = {
					p.getResources().getString(R.string.pointandclick_keepaspectratio),
					p.getResources().getString(R.string.pointandclick_showcreenunderfinger2)
				};
				boolean defaults2[] = { 
					Globals.KeepAspectRatio,
					Globals.ShowScreenUnderFinger
				};
				items = items2;
				defaults = defaults2;
			}

			AlertDialog.Builder builder = new AlertDialog.Builder(p);
			builder.setTitle(p.getResources().getString(R.string.video));
			builder.setMultiChoiceItems(items, defaults, new DialogInterface.OnMultiChoiceClickListener() 
			{
				public void onClick(DialogInterface dialog, int item, boolean isChecked) 
				{
					if( item == 0 )
						Globals.KeepAspectRatio = isChecked;
					if( item == 1 )
						Globals.ShowScreenUnderFinger = isChecked;
					if( item == 2 )
						Globals.SmoothVideo = isChecked;
					if( item == 3 )
						Globals.MultiThreadedVideo = isChecked;
				}
			});
			builder.setPositiveButton(p.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int item) 
				{
					dialog.dismiss();
					goBack(p);
				}
			});
			builder.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				public void onCancel(DialogInterface dialog)
				{
					goBack(p);
				}
			});
			AlertDialog alert = builder.create();
			alert.setOwnerActivity(p);
			alert.show();
		}
	}

	// ===============================================================================================

	static void Apply(Activity p)
	{
		if(Globals.SmoothVideo)
			nativeSetSmoothVideo();
		if( Globals.SwVideoMode && Globals.MultiThreadedVideo )
			nativeSetVideoMultithreaded();
		if( Globals.PhoneHasTrackball )
			nativeSetTrackballUsed();
		if( Globals.AppUsesMouse )
			nativeSetMouseUsed( Globals.RightClickMethod,
								Globals.ShowScreenUnderFinger ? 1 : 0,
								Globals.LeftClickMethod,
								Globals.MoveMouseWithJoystick ? 1 : 0,
								Globals.ClickMouseWithDpad ? 1 : 0,
								Globals.ClickScreenPressure,
								Globals.ClickScreenTouchspotSize,
								Globals.MoveMouseWithJoystickSpeed,
								Globals.MoveMouseWithJoystickAccel,
								Globals.LeftClickKey,
								Globals.RightClickKey,
								Globals.LeftClickTimeout,
								Globals.RightClickTimeout,
								Globals.RelativeMouseMovement ? 1 : 0,
								Globals.RelativeMouseMovementSpeed,
								Globals.RelativeMouseMovementAccel );
		if( Globals.AppUsesJoystick && (Globals.UseAccelerometerAsArrowKeys || Globals.UseTouchscreenKeyboard) )
			nativeSetJoystickUsed();
		if( Globals.AppUsesMultitouch )
			nativeSetMultitouchUsed();
		nativeSetAccelerometerSettings(Globals.AccelerometerSensitivity, Globals.AccelerometerCenterPos);
		nativeSetTrackballDampening(Globals.TrackballDampening);
		if( Globals.UseTouchscreenKeyboard )
		{
			boolean screenKbReallyUsed = false;
			for( int i = 0; i < Globals.ScreenKbControlsShown.length; i++ )
				if( Globals.ScreenKbControlsShown[i] )
					screenKbReallyUsed = true;
			if( screenKbReallyUsed )
			{
				nativeSetTouchscreenKeyboardUsed();
				nativeSetupScreenKeyboard(	Globals.TouchscreenKeyboardSize,
											Globals.TouchscreenKeyboardTheme,
											Globals.AppTouchscreenKeyboardKeysAmountAutoFire,
											Globals.TouchscreenKeyboardTransparency );
				SetupTouchscreenKeyboardGraphics(p);
				for( int i = 0; i < Globals.ScreenKbControlsShown.length; i++ )
					nativeSetScreenKbKeyUsed(i, Globals.ScreenKbControlsShown[i] ? 1 : 0);
				for( int i = 0; i < Globals.RemapScreenKbKeycode.length; i++ )
					nativeSetKeymapKeyScreenKb(i, SDL_Keys.values[Globals.RemapScreenKbKeycode[i]]);
				for( int i = 0; i < Globals.ScreenKbControlsLayout.length; i++ )
					if( Globals.ScreenKbControlsLayout[i][0] < Globals.ScreenKbControlsLayout[i][2] )
						nativeSetScreenKbKeyLayout( i, Globals.ScreenKbControlsLayout[i][0], Globals.ScreenKbControlsLayout[i][1],
							Globals.ScreenKbControlsLayout[i][2], Globals.ScreenKbControlsLayout[i][3]);
			}
			else
				Globals.UseTouchscreenKeyboard = false;
		}

		for( int i = 0; i < SDL_Keys.JAVA_KEYCODE_LAST; i++ )
			nativeSetKeymapKey(i, SDL_Keys.values[Globals.RemapHwKeycode[i]]);
		for( int i = 0; i < Globals.RemapMultitouchGestureKeycode.length; i++ )
			nativeSetKeymapKeyMultitouchGesture(i, Globals.MultitouchGesturesUsed[i] ? SDL_Keys.values[Globals.RemapMultitouchGestureKeycode[i]] : 0);
		nativeSetMultitouchGestureSensitivity(Globals.MultitouchGestureSensitivity);
		if( Globals.TouchscreenCalibration[2] > Globals.TouchscreenCalibration[0] )
			nativeSetTouchscreenCalibration(Globals.TouchscreenCalibration[0], Globals.TouchscreenCalibration[1],
				Globals.TouchscreenCalibration[2], Globals.TouchscreenCalibration[3]);

		String lang = new String(Locale.getDefault().getLanguage());
		if( Locale.getDefault().getCountry().length() > 0 )
			lang = lang + "_" + Locale.getDefault().getCountry();
		System.out.println( "libSDL: setting envvar LANGUAGE to '" + lang + "'");
		nativeSetEnv( "LANG", lang );
		nativeSetEnv( "LANGUAGE", lang );
		// TODO: get current user name and set envvar USER, the API is not availalbe on Android 1.6 so I don't bother with this
	}

	static byte [] loadRaw(Activity p,int res)
	{
		byte [] buf = new byte[65536 * 2];
		byte [] a = new byte[0];
		try{
			InputStream is = new GZIPInputStream(p.getResources().openRawResource(res));
			int readed = 0;
			while( (readed = is.read(buf)) >= 0 )
			{
				byte [] b = new byte [a.length + readed];
				System.arraycopy(a, 0, b, 0, a.length);
				System.arraycopy(buf, 0, b, a.length, readed);
				a = b;
			}
		} catch(Exception e) {};
		return a;
	}
	
	static void SetupTouchscreenKeyboardGraphics(Activity p)
	{
		if( Globals.UseTouchscreenKeyboard )
		{
			if(Globals.TouchscreenKeyboardTheme < 0)
				Globals.TouchscreenKeyboardTheme = 0;
			if(Globals.TouchscreenKeyboardTheme > 1)
				Globals.TouchscreenKeyboardTheme = 1;

			if( Globals.TouchscreenKeyboardTheme == 0 )
			{
				nativeSetupScreenKeyboardButtons(loadRaw(p, R.raw.ultimatedroid));
			}
			if( Globals.TouchscreenKeyboardTheme == 1 )
			{
				nativeSetupScreenKeyboardButtons(loadRaw(p, R.raw.simpletheme));
			}
		}
	}
	
	private static native void nativeSetTrackballUsed();
	private static native void nativeSetTrackballDampening(int value);
	private static native void nativeSetAccelerometerSettings(int sensitivity, int centerPos);
	private static native void nativeSetMouseUsed(int RightClickMethod, int ShowScreenUnderFinger, int LeftClickMethod, 
													int MoveMouseWithJoystick, int ClickMouseWithDpad, int MaxForce, int MaxRadius,
													int MoveMouseWithJoystickSpeed, int MoveMouseWithJoystickAccel,
													int leftClickKeycode, int rightClickKeycode,
													int leftClickTimeout, int rightClickTimeout,
													int relativeMovement, int relativeMovementSpeed, int relativeMovementAccel);
	private static native void nativeSetJoystickUsed();
	private static native void nativeSetMultitouchUsed();
	private static native void nativeSetTouchscreenKeyboardUsed();
	private static native void nativeSetSmoothVideo();
	private static native void nativeSetVideoMultithreaded();
	private static native void nativeSetupScreenKeyboard(int size, int theme, int nbuttonsAutoFire, int transparency);
	private static native void nativeSetupScreenKeyboardButtons(byte[] img);
	private static native void nativeInitKeymap();
	private static native int  nativeGetKeymapKey(int key);
	private static native void nativeSetKeymapKey(int javakey, int key);
	private static native int  nativeGetKeymapKeyScreenKb(int keynum);
	private static native void nativeSetKeymapKeyScreenKb(int keynum, int key);
	private static native void nativeSetScreenKbKeyUsed(int keynum, int used);
	private static native void nativeSetScreenKbKeyLayout(int keynum, int x1, int y1, int x2, int y2);
	private static native int  nativeGetKeymapKeyMultitouchGesture(int keynum);
	private static native void nativeSetKeymapKeyMultitouchGesture(int keynum, int key);
	private static native void nativeSetMultitouchGestureSensitivity(int sensitivity);
	private static native void nativeSetTouchscreenCalibration(int x1, int y1, int x2, int y2);
	public static native void  nativeSetEnv(final String name, final String value);
}

