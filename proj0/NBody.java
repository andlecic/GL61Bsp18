public class NBody{
	/** this class is used to take in planet datas and run simulations*/
	public static double readRadius(String file_name) {
		In in = new In(file_name);
		double planet_count = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String file_name) {
		In in = new In(file_name);
		int n = in.readInt();
		double radius = in.readDouble();
		Planet[] planet_array = new Planet[n];
		int i = 0;
		while (i < n) {
			double xpos = in.readDouble();
			double ypos = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String name = in.readString();
			planet_array[i] = new Planet(xpos, ypos, xV, yV, m, name);
			i = i + 1;
		}
		return planet_array;
	}

	public static void main(String[] args) {
		StdDraw.enableDoubleBuffering();
		double time = 0;
		String starfield = "images/starfield.jpg";
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		StdDraw.setScale(-radius, radius);
		// StdDraw.picture(0, 0, starfield);
		/**StdDraw.picture(p.xxPos, p.yyPos, "images/" + p.imgFileName);*/
		while (time <= T) {
			double[] xForce = new double[planets.length];
			double[] yForce = new double[planets.length];
			int i = 0;
			for (Planet p : planets) {
				xForce[i] = p.calcForceExertedByX(planets);
				yForce[i] = p.calcForceExertedByY(planets);
				// p.update(dt, xForce[i], yForce[i]);
				i = i + 1;
			}
			int k = 0;
			for (Planet p : planets) {
				p.update(dt, xForce[k], yForce[k]);
				k = k + 1;
			}
			StdDraw.picture(0, 0, starfield);
			for (Planet p : planets) {
				StdDraw.picture(p.xxPos, p.yyPos, "images/" + p.imgFileName);
			}
			StdDraw.show();
			StdDraw.pause(10);
			time = time + dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}