#version 330

in vec2 textCoord0;
in vec3 normal0;
in vec3 worldPos0;

out vec4 fragColor;

struct BaseLight
{
	vec3 color;
	float intensity;
};

struct DirectionalLight
{
	BaseLight base;
	vec3 direction;
};

uniform vec3 baseColor;
uniform vec3 eyePos;
uniform vec3 ambientLight;
uniform sampler2D sampler;

uniform float specularIntensity;
uniform float specularPower;

uniform DirectionalLight directionalLight;

vec4 calcLight(BaseLight base, vec3 direction, vec3 normal)
{
	float diffuseFactor = dot(normal, -direction);
	
	vec4 diffuseColor = vec4(0,0,0,0);
	vec4 specularColor = vec4(0,0,0,0);
	
	if(diffuseFactor > 0)
	{
		diffuseColor = vec4(base.color, 1.0) * base.intensity * diffuseFactor;
		
		vec3 directionToEye = normalize(eyePos - worldPos0);
		vec3 reflectDirection = normalize(reflect(direction, normal));
		float specularFactor = dot(directionToEye, reflectDirection);
		specularFactor = pow(specularFactor, specularPower);
		
		if(specularFactor > 0) {
			specularColor = vec4(baseColor, 1.0) * specularIntensity * specularFactor;
		}
	}
	
	return diffuseColor + specularColor;
}

vec4 calcDirectionalLight(DirectionalLight light, vec3 normal) 
{
	return calcLight(light.base, -light.direction, normal);
}

void main()
{
	vec4 totalLight = vec4(ambientLight,1);
	vec4 color = vec4(baseColor, 1);
	vec4 textureColor = texture(sampler, textCoord0.xy);
	
	vec3 normal = normalize(normal0);
	
	totalLight += calcDirectionalLight(directionalLight, normal);
	
	if(textureColor != vec4(0,0,0,0))
		color *= textureColor;
	
	fragColor = color * totalLight;
}