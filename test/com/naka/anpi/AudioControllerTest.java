package com.naka.anpi;

import java.net.MalformedURLException;

import org.junit.Test;

import junit.framework.TestCase;

public class AudioControllerTest extends TestCase {

	@Test
	public void testPlayAudioByFile_NullParam() {
		try {

			AudioController audio = new AudioController();
			audio.playAudioByFile(null);

			assertTrue(true);

		} catch (NullPointerException e) {

			fail("not cared null");

		} catch (MalformedURLException malformEx) {

		}

	}

	@Test
	public void testCreateFileUrl_NullParam() {

		try {

			AudioController audio = new AudioController();
			audio.createFileUrl(null);

			assertTrue(true);

		} catch (NullPointerException e) {

			fail("not cared null");

		}
	}

	@Test
	public void testCreateFileUrl_Regular() {

		AudioController audio = new AudioController();
		String url = audio.createFileUrl("/hoge/hoge/hoge.mp3");

		assertEquals("file://hoge/hoge/hoge.mp3", url);		
	}
	
	@Test
	public void testCreateFileUrl_NotSlashAtPre() {

		AudioController audio = new AudioController();
		String url = audio.createFileUrl("hoge/hoge/hoge.mp3");

		assertEquals("file://hoge/hoge/hoge.mp3", url);		
	}

	@Test
	public void testCreateFileUrl_ManySlashAtPre() {

		AudioController audio = new AudioController();
		String url = audio.createFileUrl("///hoge/hoge/hoge.mp3");

		assertEquals("file://hoge/hoge/hoge.mp3", url);		
	}
	
}
