/* ==================================================================
 * BCCertificateServiceTest.java - Dec 5, 2012 11:59:36 AM
 * 
 * Copyright 2007-2012 SolarNetwork.net Dev Team
 * 
 * This program is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as 
 * published by the Free Software Foundation; either version 2 of 
 * the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with this program; if not, write to the Free Software 
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 
 * 02111-1307 USA
 * ==================================================================
 */

package net.solarnetwork.common.pki.bc.test;

import static org.junit.Assert.assertNotNull;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import net.solarnetwork.common.pki.bc.BCCertificateService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test cases for the {@link BCCertificateService} class.
 * 
 * @author matt
 * @version 1.0
 */
public class BCCertificateServiceTest {

	private BCCertificateService service;
	private PublicKey publicKey;
	private PrivateKey privateKey;

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Before
	public void setup() {
		service = new BCCertificateService();
		KeyPairGenerator keyGen;
		try {
			keyGen = KeyPairGenerator.getInstance("RSA");
		} catch ( NoSuchAlgorithmException e ) {
			throw new RuntimeException(e);
		}
		keyGen.initialize(2048, new SecureRandom());
		KeyPair keypair = keyGen.generateKeyPair();
		publicKey = keypair.getPublic();
		privateKey = keypair.getPrivate();
	}

	@Test
	public void generateSelfSignedCertificate() {
		Certificate cert = service.generateCertificate("UID=1, O=SolarNetwork", publicKey, privateKey);
		assertNotNull(cert);
		log.debug("Got cert: {}", cert);
	}

}
