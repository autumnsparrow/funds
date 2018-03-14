package com.palmcommerce.funds.protocol;

import java.lang.annotation.Annotation;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.palmcommerce.funds.protocol.ProtocolElementMetaType.ProtocolElementType;
import com.palmcommerce.funds.protocol.impl.p2t.P240001;


import junit.framework.TestCase;

public class AnnotationIntrospectorTest extends TestCase {

	AnnotationIntrospector introspector;
	private static  Log logger=LogFactory.getLog(AnnotationIntrospectorTest.class);
	public AnnotationIntrospectorTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		introspector=new AnnotationIntrospector();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	

	public void testFindClassAnnotation() {
//		Annotation[] annotations= introspector.findClassAnnotation(P240001.class);
//		//fail("Not yet implemented");
//		assertNull(annotations);
	}

	public void testFindMethodAnnotation() {
		//fail("Not yet implemented");
		//Annotation[] annotations=introspector.findMethodAnnotation(P240001.class, "");
		//assertNull(annotations);
	}

	public void testFindFieldAnnotation() {
		//fail("Not yet implemented");
//		Annotation[] annotations=introspector.findFieldAnnotation(P240001.class, "userId");
//		introspector.showAnnotations(annotations);
//		assertTrue(annotations.length==1);
	}

	public void testFindFieldsSpecialAnnotataion() {
		//fail("Not yet implemented");
//		List<ProtocolElementMetaType> list= introspector.findFieldsSpecialAnnotataion(P240001.class, ProtocolElementMetaType.class);
//		ProtocolElementMetaType[] annotations=new ProtocolElementMetaType[list.size()];
	///	introspector.showAnnotations(annotations);
	}

	public void testFindFieldsMetaTypeAnnotataion() {
		//fail("Not yet implemented");
		P240001 p=new P240001();
		ProtocolMetaType[] list=introspector.findFieldsMetaTypeAnnotataion(P240001.Request.class);
//		
		for(ProtocolMetaType meta:list){
			if(meta!=null)
				logger.info(meta.toString());
		}
		logger.info(ProtocolDriverManager.isTransferModeDirect("240001"));
	}

	public void testShowAnnotations() {
		//fail("Not yet implemented");
	}

}
