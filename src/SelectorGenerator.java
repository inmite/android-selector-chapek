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

import com.intellij.openapi.vfs.VirtualFile;
import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;
import utils.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates selectors into XML.
 *
 * @author David Vávra (david@inmite.eu)
 */
public class SelectorGenerator {

	private static final String SCHEMA = "http://schemas.android.com/apk/res/android";
	private static final String NS = "android";

	public static void generate(VirtualFile newFile, List<SelectorDetector.Result> detectorResults) {
		Log.d("generating XML:");
		Element root = new Element("selector");
		root.addNamespaceDeclaration(NS, SCHEMA);
		List<String> allStatesWithoutNormal = new ArrayList<String>();
		for (SelectorDetector.Result result : detectorResults) {
			for (String state : result.states) {
				if (!state.equals(Constants.NORMAL) && !allStatesWithoutNormal.contains(state)) {
					allStatesWithoutNormal.add(state);
				}
			}
		}
		for (SelectorDetector.Result result : detectorResults) {
			Log.d("fileName=" + result.drawableName + ", states:" + result.states);
			Element item = new Element("item");
			Attribute attribute = new Attribute("drawable", "@drawable/" + result.drawableName);
			attribute.setNamespace(NS, SCHEMA);
			item.addAttribute(attribute);
			for (String state : allStatesWithoutNormal) {
				boolean defaultValue = Constants.sMapping.get(state).defaultValue;
				addState(item, Constants.sMapping.get(state).attributeName, result.states.contains(state)
						? (!defaultValue) : defaultValue);
			}
			Log.d("row=" + item.toXML());
			root.appendChild(item);
		}
		Document doc = new Document(root);
		OutputStream os = null;
		try {
			os = newFile.getOutputStream(null);
			Serializer serializer = new Serializer(os);
			serializer.setIndent(4);
			serializer.write(doc);
		} catch (IOException e) {
			Log.e(e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					Log.e(e);
				}
			}
		}

	}

	private static void addState(Element item, String state, boolean value) {
		Attribute attribute = new Attribute(state, String.valueOf(value));
		attribute.setNamespace(NS, SCHEMA);
		item.addAttribute(attribute);
	}
}
