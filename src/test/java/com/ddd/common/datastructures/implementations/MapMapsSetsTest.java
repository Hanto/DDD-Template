package com.ddd.common.datastructures.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**@author Ivan Delgado Huerta*/
public class MapMapsSetsTest
{
    private MapMapsSetsI<String, String, String>mmSets;

    // BEFORE EACH:
    //--------------------------------------------------------------------------------------------------------------------

    @BeforeEach
    public void beforeEach()
    {
        mmSets = new MapMapsSets<>(HashMap::new, HashMap::new, HashSet::new);
    }

    // TESTS:
    //--------------------------------------------------------------------------------------------------------------------

    @Nested @DisplayName("GIVEN: given a MapMapsSets")
    public class GivenMapMapSets
    {
        @Nested @DisplayName("WHEN: when it's empty")
        class WhenIsEmpty
        {
            @Test @DisplayName("THEN: retrieving any element returns null")
            void thenReturnsNull()
            {
                assertThat(mmSets).isEmpty();
                assertThat(mmSets.containsKey("unk", "unk")).isFalse();
                assertThat(mmSets.containsKey("key1", "key2")).isFalse();

                Set<String> result = mmSets.get("key1", "key2");
                assertThat(result).isNull();
            }

    // PUT Tests:
    //--------------------------------------------------------------------------------------------------------------------

            @Nested @DisplayName("WHEN: 1 element is put")
            class ElementPut
            {
                @Test @DisplayName("THEN: retrieving that key, returns the set")
                void thenRetunsSet()
                {
                    Set<String>set = Stream.of("value1", "value2", "value3").collect(Collectors.toSet());
                    mmSets.put("key1", "key2", set);

                    assertThat(mmSets).hasSize(1);
                    assertThat(mmSets.get("key1")).hasSize(1);
                    assertThat(mmSets.size()).isEqualTo(1);
                    assertThat(mmSets.containsKey("unk", "unk")).isFalse();
                    assertThat(mmSets.containsKey("key1", "key2")).isTrue();

                    Set<String>result = mmSets.get("key1", "key2");
                    assertThat(result).hasSize(3).isEqualTo(set);
                }

                @Nested @DisplayName("WHEN: N elements are put with some different Keys")
                class ElemenentsPut
                {
                    @Test @DisplayName("THEN: retrieving the key, returns the set")
                    void thenReturnsSets()
                    {
                        Set<String>set1 = Stream.of("value1", "value2", "value3").collect(Collectors.toSet());
                        Set<String>set2 = Stream.of("value4", "value5", "value6").collect(Collectors.toSet());
                        Set<String>set3 = Stream.of("value7", "value8", "value9").collect(Collectors.toSet());

                        mmSets.put("key01", "key1", set1);
                        mmSets.put("key01", "key1", set1);
                        mmSets.put("key02", "key2", set1);
                        mmSets.put("key02", "key2", set2);
                        mmSets.put("key03", "key3", set1);
                        mmSets.put("key03", "key3", set3);

                        assertThat(mmSets).hasSize(3);
                        assertThat(mmSets.get("key01")).hasSize(1);
                        assertThat(mmSets.get("key02")).hasSize(1);
                        assertThat(mmSets.get("key03")).hasSize(1);
                        assertThat(mmSets.containsKey("unk", "unk")).isFalse();
                        assertThat(mmSets.containsKey("key01", "key1")).isTrue();
                        assertThat(mmSets.containsKey("key02", "key2")).isTrue();
                        assertThat(mmSets.containsKey("key03", "key3")).isTrue();

                        Set<String>result1 = mmSets.get("key01", "key1");
                        assertThat(result1).isEqualTo(set1);

                        Set<String>result2 = mmSets.get("key02", "key2");
                        assertThat(result2).isEqualTo(set2);

                        Set<String>result3 = mmSets.get("key03", "key3");
                        assertThat(result3).isEqualTo(set3);
                    }
                }
            }

    // ADD Tests:
    //--------------------------------------------------------------------------------------------------------------------

            @Nested @DisplayName("WHEN: 1 element is added")
            class ElementAdded
            {
                @Test @DisplayName("THEN: retrieving that key, returns a set with it")
                void thenReturnsSet()
                {
                    mmSets.add("key1", "key2", "value1");

                    assertThat(mmSets).hasSize(1);
                    assertThat(mmSets.get("key1")).hasSize(1);
                    assertThat(mmSets.containsKey("unk", "unk")).isFalse();
                    assertThat(mmSets.containsKey("key1", "key2")).isTrue();

                    Set<String> result = mmSets.get("key1", "key2");
                    assertThat(result).hasSize(1);
                    assertThat(result.stream().findFirst().orElse(null)).isEqualTo("value1");
                }

                @Nested @DisplayName("WHEN: N elements are added with the same Keys")
                class ElementsAddedSameKey
                {
                    @Test @DisplayName("THEN: retrieving the key, returns a set with them")
                    void thenReturnsSet()
                    {
                        mmSets.add("key1", "key2", "value1");
                        mmSets.add("key1", "key2", "value1");
                        mmSets.add("key1", "key2", "value2");
                        mmSets.add("key1", "key2", "value2");
                        mmSets.add("key1", "key2", "value3");
                        mmSets.add("key1", "key2", "value3");

                        assertThat(mmSets).hasSize(1);
                        assertThat(mmSets.get("key1")).hasSize(1);
                        assertThat(mmSets.containsKey("unk", "unk")).isFalse();
                        assertThat(mmSets.containsKey("key1", "key2")).isTrue();

                        Set<String> result = mmSets.get("key1", "key2");
                        assertThat(result)
                            .hasSize(3)
                            .contains("value1", "value2", "value3");
                    }
                }

                @Nested @DisplayName("WHEN: N elements are added with all different Keys")
                class ElementsAddedDifferentKeys
                {
                    @Test @DisplayName("THEN: retrieving the keys, returns a set with them")
                    void thenReturnsSet()
                    {
                        mmSets.add("key01", "key1", "value1");
                        mmSets.add("key01", "key1", "value1");
                        mmSets.add("key02", "key2", "value2");
                        mmSets.add("key02", "key2", "value2");
                        mmSets.add("key03", "key3", "value3");
                        mmSets.add("key03", "key3", "value3");


                        assertThat(mmSets).hasSize(3);
                        assertThat(mmSets.get("key01")).hasSize(1);
                        assertThat(mmSets.get("key02")).hasSize(1);
                        assertThat(mmSets.get("key03")).hasSize(1);
                        assertThat(mmSets.containsKey("unk", "unk")).isFalse();
                        assertThat(mmSets.containsKey("key01", "key1")).isTrue();
                        assertThat(mmSets.containsKey("key02", "key2")).isTrue();
                        assertThat(mmSets.containsKey("key03", "key3")).isTrue();

                        Set<String> result1 = mmSets.get("key01", "key1");
                        assertThat(result1)
                            .hasSize(1)
                            .contains("value1");

                        Set<String> result2 = mmSets.get("key02", "key2");
                        assertThat(result2)
                            .hasSize(1)
                            .contains("value2");
                        Set<String> result3 = mmSets.get("key03", "key3");
                        assertThat(result3)
                            .hasSize(1)
                            .contains("value3");
                    }
                }

                @Nested @DisplayName("WHEN: N elements are added with some different Keys")
                class ElementsAddedMixedKeys
                {
                    @Test @DisplayName("THEN: retrieving the keys, returns a set with them")
                    void thenReturnsSet()
                    {
                        mmSets.add("key01", "key1", "value1");
                        mmSets.add("key01", "key1", "value1");
                        mmSets.add("key01", "key1", "value2");
                        mmSets.add("key01", "key1", "value2");
                        mmSets.add("key01", "key2", "value3");
                        mmSets.add("key01", "key2", "value3");
                        mmSets.add("key01", "key2", "value4");
                        mmSets.add("key01", "key2", "value4");

                        mmSets.add("key02", "key1", "value5");
                        mmSets.add("key02", "key1", "value5");
                        mmSets.add("key02", "key1", "value6");
                        mmSets.add("key02", "key1", "value6");
                        mmSets.add("key02", "key2", "value7");
                        mmSets.add("key02", "key2", "value7");
                        mmSets.add("key02", "key2", "value8");
                        mmSets.add("key02", "key2", "value8");

                        mmSets.add("key03", "key1", "value9");
                        mmSets.add("key03", "key1", "value9");
                        mmSets.add("key03", "key1", "value10");
                        mmSets.add("key03", "key1", "value10");
                        mmSets.add("key03", "key2", "value11");
                        mmSets.add("key03", "key2", "value11");
                        mmSets.add("key03", "key2", "value12");
                        mmSets.add("key03", "key2", "value12");

                        assertThat(mmSets).hasSize(3);
                        assertThat(mmSets.get("key01")).hasSize(2);
                        assertThat(mmSets.get("key02")).hasSize(2);
                        assertThat(mmSets.get("key03")).hasSize(2);
                        assertThat(mmSets.containsKey("unk", "unk")).isFalse();
                        assertThat(mmSets.containsKey("key01", "key1")).isTrue();
                        assertThat(mmSets.containsKey("key01", "key2")).isTrue();
                        assertThat(mmSets.containsKey("key02", "key1")).isTrue();
                        assertThat(mmSets.containsKey("key02", "key2")).isTrue();
                        assertThat(mmSets.containsKey("key03", "key1")).isTrue();
                        assertThat(mmSets.containsKey("key03", "key2")).isTrue();

                        Set<String> result1 = mmSets.get("key01", "key1");
                        assertThat(result1)
                            .hasSize(2)
                            .contains("value1", "value2");

                        Set<String> result1b = mmSets.get("key01", "key2");
                        assertThat(result1b)
                            .hasSize(2)
                            .contains("value3", "value4");

                        Set<String> result2 = mmSets.get("key02", "key1");
                        assertThat(result2)
                            .hasSize(2)
                            .contains("value5", "value6");

                        Set<String> result2b = mmSets.get("key02", "key2");
                        assertThat(result2b)
                            .hasSize(2)
                            .contains("value7", "value8");

                        Set<String> result3 = mmSets.get("key03", "key1");
                        assertThat(result3)
                            .hasSize(2)
                            .contains("value9", "value10");

                        Set<String> result3b = mmSets.get("key03", "key2");
                        assertThat(result3b)
                            .hasSize(2)
                            .contains("value11", "value12");
                    }
                }
            }
        }
    }
}
