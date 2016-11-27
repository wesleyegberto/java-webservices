package com.github.wesleyegberto.business.person.boundary;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.wesleyegberto.business.person.entity.Person;

@RunWith(Arquillian.class)
public class PersonResourceTest
{

   @Inject
   private PersonResource personresource;

   @Deployment
   public static JavaArchive createDeployment()
   {
      return ShrinkWrap.create(JavaArchive.class, "test.jar")
    		.addClasses(Person.class, PersonManager.class)
            .addClass(PersonResource.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
			.addAsManifestResource("WEB-INF/test-persistence.xml", "persistence.xml");
   }

   @Test
   public void should_be_deployed()
   {
      Assert.assertNotNull(personresource);
   }
}
