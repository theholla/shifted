package models;

import com.epicodus.signin2.models.BikeCollective;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml")
public class BikeCollectiveTest extends TestCase {

    @Test
    public void bikeCollectiveInstantiates() {
        BikeCollective bikeCollective = new BikeCollective("Bikerowave", "bike@bike.bike", "123", "Safety!");
        assertThat(bikeCollective, instanceOf(BikeCollective.class));
    }
}
