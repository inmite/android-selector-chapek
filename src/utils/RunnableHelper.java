package utils;/*
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
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;

/**
 * Helper class for launching filesystem write operations on background.
 * @author David Vávra (david@inmite.eu)
 */
public class RunnableHelper
{
	public static void runReadCommand(Project project, Runnable cmd)
	{
		CommandProcessor.getInstance().executeCommand(project, new ReadAction(cmd), "Foo", "Bar");
	}

	public static void runWriteCommand(Project project, Runnable cmd)
	{
		CommandProcessor.getInstance().executeCommand(project, new WriteAction(cmd), "Foo", "Bar");
	}

	static class ReadAction implements Runnable
	{
		ReadAction(Runnable cmd)
		{
			this.cmd = cmd;
		}

		public void run()
		{
			ApplicationManager.getApplication().runReadAction(cmd);
		}

		Runnable cmd;
	}

	static class WriteAction implements Runnable
	{
		WriteAction(Runnable cmd)
		{
			this.cmd = cmd;
		}

		public void run()
		{
			ApplicationManager.getApplication().runWriteAction(cmd);
		}

		Runnable cmd;
	}

	private RunnableHelper() {}
}
