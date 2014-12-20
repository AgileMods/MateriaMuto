/**
 * This file is part of MateriaMuto, licensed under the MIT License (MIT).
 *
 * Copyright (c) AgileMods <http://www.agilemods.com>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.agilemods.materiamuto.common.core.helpers;

/**
 * Mostly pulled from Unity's Mathfx class
 */
public class MathFX {

    /* UTILITY METHODS */
    public static float clamp(float low, float high, float value) {
        if (value < low) {
            return low;
        } else if (value > high) {
            return high;
        } else {
            return value;
        }
    }

    /* FX */
    public static float lerp(float start, float end, float value) {
        return (start + (value * (end - start)));
    }

    public static float sinerp(float start, float end, float value) {
        return lerp(start, end, (float) Math.sin(value * Math.PI * 0.5F));
    }

    public static float berp(float start, float end, float value) {
        value = clamp(0, 1, value);
        value =
                (float) ((Math.sin(value * Math.PI * (0.2f + 2.5f * value * value * value)) * Math.pow(1f - value, 2.2f) + value) * (1f + (1.2f * (1f
                                                                                                                                                   - value))));
        return start + (end - start) * value;
    }

}