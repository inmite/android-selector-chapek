/*
 * Copyright 2013 Inmite s.r.o. (www.inmite.eu).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.awt.RelativePoint;
import utils.Log;

import java.util.regex.Matcher;

/**
 * Action which is launched when user clicks the menu item. It handles related UI-stuff.
 *
 * @author David Vávra (david@inmite.eu)
 */
public class SelectorChapekAction extends AnAction {

	@Override
	public void actionPerformed(AnActionEvent e) {
		Log.d("---- Start - menu item clicked ----");
		VirtualFile selectedFile = DataKeys.VIRTUAL_FILE.getData(e.getDataContext());
		if (isCorrectFolderSelected(selectedFile)) {
			new SelectorDetector(e.getProject()).detectAndCreateSelectors(selectedFile);
			showInfoDialog("Selectors were generated into 'drawable' folder", e);
		} else {
			if (selectedFile != null) {
				showErrorDialog("You need to select folder with image resources, for example 'drawables-xhdpi' " +
						"" + selectedFile.getName(), e);
			}
		}
	}

	@Override
	public void update(AnActionEvent e) {
		VirtualFile selectedFile = DataKeys.VIRTUAL_FILE.getData(e.getDataContext());
		e.getPresentation().setEnabled(isCorrectFolderSelected(selectedFile));
	}

	private boolean isCorrectFolderSelected(VirtualFile selectedFile) {
		if (selectedFile != null && selectedFile.isDirectory()) {
			Matcher matcher = Constants.VALID_FOLDER_PATTERN.matcher(selectedFile.getName());
			if (matcher.matches()) {
				return true;
			}
		}
		return false;
	}

	private void showInfoDialog(String text, AnActionEvent e) {
		StatusBar statusBar = WindowManager.getInstance().getStatusBar(DataKeys.PROJECT.getData(e.getDataContext()));

		if (statusBar != null) {
			JBPopupFactory.getInstance()
					.createHtmlTextBalloonBuilder(text, MessageType.INFO, null)
					.setFadeoutTime(10000)
					.createBalloon()
					.show(RelativePoint.getCenterOf(statusBar.getComponent()),
							Balloon.Position.atRight);
		}
	}

	private void showErrorDialog(String text, AnActionEvent e) {
		StatusBar statusBar = WindowManager.getInstance().getStatusBar(DataKeys.PROJECT.getData(e.getDataContext()
		));

		if (statusBar != null) {
			JBPopupFactory.getInstance()
					.createHtmlTextBalloonBuilder(text, MessageType.ERROR, null)
					.setFadeoutTime(10000)
					.createBalloon()
					.show(RelativePoint.getCenterOf(statusBar.getComponent()),
							Balloon.Position.atRight);
		}
	}
}
