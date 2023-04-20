module.exports = {
    env: {
        browser: true,
        es2021: true,
        jest: true,
    },
    globals: {
        page: true,
        browser: true,
        context: true,
        jestPuppeteer: true,
    },
    extends: [
        "plugin:react/recommended",
        "airbnb",
    ],
    parserOptions: {
        ecmaFeatures: {
            jsx: true,
        },
        ecmaVersion: "latest",
        sourceType: "module",
    },
    plugins: [
        "react",
    ],
    rules: {
        "max-len": ["warn", { code: 150 }],
        semi: ["error", "always"],
        quotes: ["error", "double"],
        "import/prefer-default-export": "off",
        "linebreak-style": "off",
        "jsx-a11y/label-has-associated-control": ["error", {
            required: {
                some: ["nesting", "id"],
            },
        }],
        "no-useless-escape": "off",
        "react/jsx-props-no-spreading": "warn",
        "react/no-unstable-nested-components": "warn",
        "jsx-a11y/click-events-have-key-events": "warn",
        "jsx-a11y/no-static-element-interactions": "warn",
        "jsx-a11y/label-has-for": ["error", {
            required: {
                some: ["nesting", "id"],
            },
        }],
        "react/prop-types": 0,
        "no-underscore-dangle": "off",
        // Indent with 4 spaces
        indent: ["error", 4],

        // Indent JSX with 4 spaces
        "react/jsx-indent": ["error", 4],

        // Indent props with 4 spaces
        "react/jsx-indent-props": ["error", 4],
        "no-nested-ternary": "off",
    },
    settings: {
        "import/resolver": {
            node: {
                extensions: [".js", ".jsx", ".ts", ".tsx"],
            },
        },
    },
};
