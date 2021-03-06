/*
 * Music Visualizations: http:/github.com/michaelbrooks/music-visualization
 * Copyright 2012, Michael Brooks. BSD License.
 */

package music.data;

/**
 * @author michael
 */
public class SpectralFluxCalculator {

    private static SpectralFluxCalculator calculator = null;
    double currentFlux = 0;
    PS oldPowerSpectrum = null;

    private SpectralFluxCalculator() {
        calculator = this;
    }

    public static SpectralFluxCalculator getInstance() {
        if (calculator == null)
            calculator = new SpectralFluxCalculator();
        return calculator;
    }

    public double getValue() {
        return currentFlux;
    }

    public double norm1(double[] a, double[] b) {
        double sum = 0;
        double total = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.abs(a[i] - b[i]);
            total += Math.max(a[i], b[i]);
            //total += a[i] + b[i];
        }
        return sum / total;
    }

    public double newValue(PS newPowerSpectrum) {
        if (oldPowerSpectrum != null) {
            currentFlux = norm1(newPowerSpectrum.getRawData(), oldPowerSpectrum.getRawData());
        }

        oldPowerSpectrum = newPowerSpectrum;
        return currentFlux;
    }
}
