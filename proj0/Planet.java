public class Planet {
	/** The basic methods to calculate the xy-cord and forces between planets */
	public double xxPos; 
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static double G_constant = 6.67e-11;
	

	public Planet(double xP, double yP, double xV,
				double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double x_distance = this.xxPos - p.xxPos;
		double y_distance = this.yyPos - p.yyPos;
		return Math.sqrt(Math.pow(x_distance, 2) + Math.pow(y_distance, 2));
	}


	public double calcForceExertedBy(Planet p) {
		return Planet.G_constant * this.mass * p.mass / Math.pow(this.calcDistance(p), 2);
	}

	public double calcForceExertedByX(Planet p) {
		double x_distance = p.xxPos - this.xxPos;
		double fx_now = this.calcForceExertedBy(p) * x_distance / this.calcDistance(p);
		return fx_now;
	}
	public double calcForceExertedByY(Planet p) {
		double y_distance = p.yyPos - this.yyPos;
		double fy_now = this.calcForceExertedBy(p) * y_distance / this.calcDistance(p);
		return fy_now;
	}

	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double fx_now = 0;
		for (Planet p : allPlanets) {
			if (this.equals(p)) {
				continue;
			} else {
				fx_now = fx_now + calcForceExertedByX(p);
			}
		}
		return fx_now;
	}
	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double fy_now = 0;
		for (Planet p : allPlanets) {
			if (this.equals(p)) {
				continue;
			} else {
				fy_now = fy_now + calcForceExertedByY(p);
			}
		}
		return fy_now;
	}

	public void update(double dt, double fX, double fY) {
		double x_acceleration = fX / this.mass;
		double y_acceleration = fY / this.mass;
		this.xxVel = this.xxVel + dt * x_acceleration;
		this.yyVel = this.yyVel + dt * y_acceleration;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}
	 public void draw() {
	 	StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
	 }
}
