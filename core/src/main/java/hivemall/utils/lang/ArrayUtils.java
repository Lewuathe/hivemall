/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package hivemall.utils.lang;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

public final class ArrayUtils {

    /**
     * The index value when an element is not found in a list or array: <code>-1</code>. This value
     * is returned by methods in this class and can also be used in comparisons with values returned
     * by various method from {@link java.util.List}.
     */
    public static final int INDEX_NOT_FOUND = -1;

    private ArrayUtils() {}

    public static double[] set(double[] src, final int index, final double value) {
        if (index >= src.length) {
            src = Arrays.copyOf(src, src.length * 2);
        }
        src[index] = value;
        return src;
    }

    public static <T> T[] set(T[] src, final int index, final T value) {
        if (index >= src.length) {
            src = Arrays.copyOf(src, src.length * 2);
        }
        src[index] = value;
        return src;
    }

    public static float[] toArray(final List<Float> lst) {
        final int ndim = lst.size();
        final float[] ary = new float[ndim];
        int i = 0;
        for (float f : lst) {
            ary[i++] = f;
        }
        return ary;
    }

    public static Integer[] toObject(final int[] array) {
        final Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    public static List<Integer> toList(final int[] array) {
        Integer[] v = toObject(array);
        return Arrays.asList(v);
    }

    public static Long[] toObject(final long[] array) {
        final Long[] result = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    public static List<Long> toList(final long[] array) {
        Long[] v = toObject(array);
        return Arrays.asList(v);
    }

    public static Float[] toObject(final float[] array) {
        final Float[] result = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    public static List<Float> toList(final float[] array) {
        Float[] v = toObject(array);
        return Arrays.asList(v);
    }

    public static Double[] toObject(final double[] array) {
        final Double[] result = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    public static List<Double> toList(final double[] array) {
        Double[] v = toObject(array);
        return Arrays.asList(v);
    }

    public static <T> void shuffle(final T[] array) {
        shuffle(array, array.length);
    }

    public static <T> void shuffle(final T[] array, final Random rnd) {
        shuffle(array, array.length, rnd);
    }

    public static <T> void shuffle(final T[] array, final int size) {
        Random rnd = new Random();
        shuffle(array, size, rnd);
    }

    /**
     * Fisher–Yates shuffle
     * 
     * @link http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
     */
    public static <T> void shuffle(@Nonnull final T[] array, final int size,
            @Nonnull final Random rnd) {
        for (int i = size; i > 1; i--) {
            int randomPosition = rnd.nextInt(i);
            swap(array, i - 1, randomPosition);
        }
    }

    public static void shuffle(@Nonnull final int[] array, @Nonnull final Random rnd) {
        for (int i = array.length; i > 1; i--) {
            int randomPosition = rnd.nextInt(i);
            swap(array, i - 1, randomPosition);
        }
    }

    public static void swap(@Nonnull final Object[] arr, final int i, final int j) {
        Object tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void swap(@Nonnull final int[] arr, final int i, final int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static Object[] subarray(Object[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;
        Class<?> type = array.getClass().getComponentType();
        if (newSize <= 0) {
            return (Object[]) Array.newInstance(type, 0);
        }
        Object[] subarray = (Object[]) Array.newInstance(type, newSize);
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    public static void fill(final float[] a, final Random rand) {
        for (int i = 0, len = a.length; i < len; i++) {
            a[i] = rand.nextFloat();
        }
    }

    public static int indexOf(final int[] array, final int valueToFind, int startIndex, int endIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        final int til = Math.min(endIndex, array.length);
        if (startIndex < 0 || startIndex > til) {
            throw new IllegalArgumentException("Illegal startIndex: " + startIndex);
        }
        for (int i = startIndex; i < til; i++) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    public static byte[] copyOf(final byte[] original, final int newLength) {
        final byte[] copy = new byte[newLength];
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength));
        return copy;
    }

    public static int[] copyOf(final int[] src) {
        int len = src.length;
        int[] dest = new int[len];
        System.arraycopy(src, 0, dest, 0, len);
        return dest;
    }

    public static void copy(final int[] src, final int[] dest) {
        if (src.length != dest.length) {
            throw new IllegalArgumentException("src.legnth '" + src.length + "' != dest.length '"
                    + dest.length + "'");
        }
        System.arraycopy(src, 0, dest, 0, src.length);
    }

    public static int[] append(int[] array, int currentSize, int element) {
        if (currentSize + 1 > array.length) {
            int[] newArray = new int[currentSize * 2];
            System.arraycopy(array, 0, newArray, 0, currentSize);
            array = newArray;
        }
        array[currentSize] = element;
        return array;
    }

    public static int[] insert(int[] array, int currentSize, int index, int element) {
        if (currentSize + 1 <= array.length) {
            System.arraycopy(array, index, array, index + 1, currentSize - index);
            array[index] = element;
            return array;
        }
        int[] newArray = new int[currentSize * 2];
        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = element;
        System.arraycopy(array, index, newArray, index + 1, array.length - index);
        return newArray;
    }

    public static boolean equals(@Nonnull final float[] array, final float value) {
        for (int i = 0, size = array.length; i < size; i++) {
            if (array[i] != value) {
                return false;
            }
        }
        return true;
    }

    public static boolean almostEquals(@Nonnull final float[] array, final float expected) {
        return equals(array, expected, 1E-15f);
    }

    public static boolean equals(@Nonnull final float[] array, final float expected,
            final float delta) {
        for (int i = 0, size = array.length; i < size; i++) {
            float actual = array[i];
            if (Math.abs(expected - actual) > delta) {
                return false;
            }
        }
        return true;
    }

}
